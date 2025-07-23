package com.hms.appointment.service;

import com.hms.appointment.clients.ProfileClient;
import com.hms.appointment.dto.Appointment_Record_Dto;
import com.hms.appointment.dto.DoctorName;
import com.hms.appointment.dto.RecordDetails;
import com.hms.appointment.entity.Appointment;
import com.hms.appointment.entity.Appointment_Record;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.repository.Appointment_RecordRepo;
import com.hms.appointment.utility.StringListConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class Appointment_RecordServiceImpl implements Appointment_RecordService {

    private final Appointment_RecordRepo appointmentRecordRepo;
    private final PrescriptionService prescriptionService;
    private final ProfileClient profileClient;

    @Override
    public Long createAppointmentRecord(Appointment_Record_Dto appointmentRecordDto) throws HmsException {
        Optional<Appointment_Record> byAppointmentId = appointmentRecordRepo.findByAppointment_Id(appointmentRecordDto.getAppointmentId());
        if (byAppointmentId.isPresent()) {
            throw new HmsException("APPOINTMENT_RECORD_ALREADY_EXISTS");
        }
        appointmentRecordDto.setCreatedAt(LocalDateTime.now());
        Long id = appointmentRecordRepo.save(appointmentRecordDto.toEntity()).getId();
        if (appointmentRecordDto.getPrescriptionDto() != null) {
            appointmentRecordDto.getPrescriptionDto().setAppointmentId(appointmentRecordDto.getAppointmentId());
            prescriptionService.savePrescription(appointmentRecordDto.getPrescriptionDto());
        }
        return id;
    }

    @Override
    public void updateAppointmentRecord(Appointment_Record_Dto appointmentRecordDto) throws HmsException {
        Appointment_Record existing = appointmentRecordRepo.findById(appointmentRecordDto.getId())
                .orElseThrow(() -> new HmsException("APPOINTMENT_RECORD_NOT_FOUND"));
        existing.setNotes(appointmentRecordDto.getNotes());
        existing.setDiagnosis(appointmentRecordDto.getDiagnosis());
        existing.setFollowUpdate(appointmentRecordDto.getFollowUpdate());
        existing.setSymptoms(StringListConverter.convertToString(appointmentRecordDto.getSymptoms()));
        existing.setTests(StringListConverter.convertToString(appointmentRecordDto.getTests()));
        existing.setReferral(appointmentRecordDto.getReferral());
        existing.setAppointment(new Appointment(appointmentRecordDto.getAppointmentId()));
        appointmentRecordRepo.save(existing);

    }

    @Override
    public Appointment_Record_Dto getAppointmentRecordByAppointmentId(Long appointmentId) throws HmsException {
        return appointmentRecordRepo.findByAppointment_Id(appointmentId)
                .orElseThrow(() -> new HmsException("APPOINTMENT_RECORD_NOT_FOUND")).toDto();
    }

    @Override
    public Appointment_Record_Dto getAppointmentRecordDetailsByAppointmentId(Long appointmentId) throws HmsException {
        Appointment_Record_Dto appointmentRecordNotFound = appointmentRecordRepo.findByAppointment_Id(appointmentId).orElseThrow(() -> new HmsException("APPOINTMENT_RECORD_NOT_FOUND")).toDto();
        appointmentRecordNotFound.setPrescriptionDto(prescriptionService.getPrescriptionByAppointmentId(appointmentId));
        return appointmentRecordNotFound;
    }

    @Override
    public Appointment_Record_Dto getAppointmentRecordById(Long recordId) throws HmsException {
        return appointmentRecordRepo.findById(recordId)
                .orElseThrow(() -> new HmsException("APPOINTMENT_RECORD_NOT_FOUND")).toDto();
    }

    @Override
    public List<RecordDetails> getRecordByPatientId(Long patientId) throws HmsException {

        List<Appointment_Record> records = appointmentRecordRepo.findByPatientId(patientId);

        List<RecordDetails> list = records.stream()
                .map(Appointment_Record::toRecordDetails)
                .toList();
        List<Long> doctorIds = list.stream()
                .map(RecordDetails::getDoctorId)
                .distinct()
                .toList();

        List<DoctorName> doctors = profileClient.getDoctorsById(doctorIds);
        Map<Long, String> collect = doctors.stream()
                .collect(Collectors.toMap(DoctorName::getId, DoctorName::getName));
        list.forEach(record -> {
            String doctorName = collect.get(record.getDoctorId());
            if (doctorName != null) {
                record.setDoctorName(doctorName);
            } else {
                record.setDoctorName("Unknown Doctor");
            }
        });
        return list;
    }

    @Override
    public Boolean isRecordExists(Long appointmentId) throws HmsException {
        return appointmentRecordRepo.existsByAppointment_Id(appointmentId);
    }
}
