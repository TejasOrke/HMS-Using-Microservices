package com.hms.appointment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.appointment.clients.ProfileClient;
import com.hms.appointment.dto.DoctorName;
import com.hms.appointment.dto.PrescriptionDetails;
import com.hms.appointment.dto.PrescriptionDto;
import com.hms.appointment.entity.Prescription;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.repository.PrescriptionRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepo prescriptionRepo;
    private final MedicineService medicineService;
    private final ProfileClient profileClient;

    @Override
    public Long savePrescription(PrescriptionDto prescriptionDto) throws HmsException {
        prescriptionDto.setPrescriptionDate(LocalDate.now());
        Long id = prescriptionRepo.save(prescriptionDto.toEntity()).getId();
        prescriptionDto.getMedicines().forEach(medicine -> {
            medicine.setPrescriptionId(id);
        });

        medicineService.saveAllMedicines(prescriptionDto.getMedicines());
        return id;
    }

    @Override
    public PrescriptionDto getPrescriptionByAppointmentId(Long appointmentId) throws HmsException {
        PrescriptionDto prescriptionNotFound = prescriptionRepo.findByAppointment_Id(appointmentId).orElseThrow(() -> new HmsException("PRESCRIPTION_NOT_FOUND"))
                .toDto();
        prescriptionNotFound.setMedicines(medicineService.getAllMedicinesByPrescriptionId(prescriptionNotFound.getId()));
        return prescriptionNotFound;
    }

    @Override
    public PrescriptionDto getPrescriptionById(Long prescriptionId) throws HmsException {
        PrescriptionDto prescriptionNotFound = prescriptionRepo.findById(prescriptionId).orElseThrow(() -> new HmsException("PRESCRIPTION_NOT_FOUND"))
                .toDto();
        prescriptionNotFound.setMedicines(medicineService.getAllMedicinesByPrescriptionId(prescriptionNotFound.getId()));
        return prescriptionNotFound;
    }

    @Override
    public List<PrescriptionDetails> getPrescriptionsByPatientId(Long patientId) throws HmsException {
        List<Prescription> prescriptions = prescriptionRepo.findAllByPatientId(patientId);

        List<PrescriptionDetails> prescriptionDetails = prescriptions.stream()
                .map(Prescription::toPrescriptionDetails)
                .toList();
        prescriptionDetails.forEach(details -> {
            try {
                details.setMedicines(medicineService.getAllMedicinesByPrescriptionId(details.getId()));
            } catch (HmsException e) {
                throw new RuntimeException(e);
            }
        });
        List<Long> doctorIds = prescriptionDetails.stream()
                .map(PrescriptionDetails::getDoctorId)
                .distinct()
                .toList();
        List<DoctorName> doctorNames = profileClient.getDoctorsById(doctorIds);
        Map<Long, String> collect
                = doctorNames.stream()
                        .collect(Collectors.toMap(DoctorName::getId, DoctorName::getName));

        prescriptionDetails.forEach(details -> {
            String doctorName = collect.get(details.getDoctorId());
            if (doctorName != null) {
                details.setDoctorName(doctorName);
            } else {
                details.setDoctorName("Unknown Doctor");
            }
        });
        return prescriptionDetails;
    }
}
