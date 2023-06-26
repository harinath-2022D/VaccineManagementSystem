package com.example.VaccineManagement.Dtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
@Data
public class AppointmentReqDto {
    private int docId;

    private int userId;

    private Date appointmentDate;

    private LocalTime appointmentTime;
}
