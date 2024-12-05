package com.springboot.restJPA.controller;

import com.springboot.restJPA.models.Quarto;
import com.springboot.restJPA.models.Reserva;
import com.springboot.restJPA.repository.QuartoRepository;
import com.springboot.restJPA.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reservas")
public class ReserveController {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    @PostMapping
    public String reserveRoom(@RequestBody Reserva reserva) {
        Quarto quarto = quartoRepository.findById(reserva.getQuarto().getId()).orElse(null);

        if (quarto == null) {
            return "not found";
        }

        if (isQuartoReservation(quarto, reserva.getParsedCheckIn(), reserva.getParsedCheckOut())) {
            return "already there room for this datas";
        }

        reserva.setQuarto(quarto);
        reserveRepository.save(reserva);

        quarto.setDisponivel(false);
        quartoRepository.save(quarto);

        return "success";
    }

    private boolean isQuartoReservation(Quarto quarto, LocalDate checkIn, LocalDate checkOut) {
        for (Reserva r : reserveRepository.findAll()) {
            if (r.getQuarto().equals(quarto) && (
                    (checkIn.isBefore(r.getParsedCheckOut()) && checkIn.isAfter(r.getParsedCheckIn())) ||
                            (checkOut.isAfter(r.getParsedCheckIn()) && checkOut.isBefore(r.getParsedCheckOut())))) {
                return true;
            }
        }
        return false;
    }
}
