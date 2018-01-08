package com.beautysalon.BeautySalon.Controllers;

import com.beautysalon.BeautySalon.Entities.Bookings;
import com.beautysalon.BeautySalon.Repositories.BeautyProcedureRepository;
import com.beautysalon.BeautySalon.Repositories.BookingsRepository;
import com.beautysalon.BeautySalon.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
public class BookingsController {

    @Autowired
    BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingsRepository bookingsRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    List<Bookings> getBookings(){
        return (List<Bookings>) bookingsRepository.findAll();
    }

    public @ResponseBody
    @RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
    Bookings newBooking (
            @RequestParam (value = "start_time") String startTime,
            @RequestParam (value = "beauty_procedure_id") long procedureId,
            @RequestParam (value = "user_id") long userId) {
        Bookings newBooking = new Bookings(startTime,beautyProcedureRepository.findById(procedureId),userRepository.findById(userId));
        bookingsRepository.save(newBooking);
        return newBooking;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Bookings deleteBooking(@PathVariable(value = "id") long id) {
        bookingsRepository.delete(id);
        return bookingsRepository.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public Bookings updateBooking(
            @PathVariable(value = "id") long id,
            @RequestParam (value = "start_time") String startTime,
            @RequestParam (value = "beauty_procedure_id") long procedureId,
            @RequestParam (value = "user_id") long userId) {
        Bookings updatedBooking = bookingsRepository.findById(id);
        updatedBooking.setStartTime(startTime);
        bookingsRepository.save(updatedBooking);

        return updatedBooking;
    }




}