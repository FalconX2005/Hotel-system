package uz.pdp.hotelsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.BookingRoom;
import uz.pdp.hotelsystem.entity.Payment;
import uz.pdp.hotelsystem.entity.Room;
import uz.pdp.hotelsystem.enums.StatusBooking;
import uz.pdp.hotelsystem.exception.RestException;
import uz.pdp.hotelsystem.payload.PaymentDTO;
import uz.pdp.hotelsystem.repository.BookingRepository;
import uz.pdp.hotelsystem.repository.PaymentRepository;
import uz.pdp.hotelsystem.repository.RoomRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    @PostMapping("/pay")
    public ApiResult<PaymentDTO> paymentForBooking(@RequestBody PaymentDTO paymentDTO ) {
        Optional<BookingRoom> byId = bookingRepository.findById(paymentDTO.getBookingRoomId());

        if(byId.isEmpty()){
            throw RestException.error("Booking room not found");
        }

        BookingRoom bookingRoom = byId.get();

        if(!bookingRoom.getRoom().getIs_available()){
            throw RestException.error("Booking room is not available");
        }

        Payment payment = new Payment();
        payment.setBookingRoom(bookingRoom);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());

        Payment savedPayment = paymentRepository.save(payment);

        bookingRoom.setStatus(StatusBooking.PAID);
        bookingRepository.save(bookingRoom);

        Room room = bookingRoom.getRoom();
        room.setIs_available(true);
        roomRepository.save(room);

        PaymentDTO response = new PaymentDTO();
        response.setId(savedPayment.getId());
        response.setBookingRoomId(savedPayment.getBookingRoom().getId());
        response.setAmount(savedPayment.getAmount());
        response.setPaymentDate(savedPayment.getPaymentDate());
        response.setPaymentMethod(savedPayment.getPaymentMethod());

        return ApiResult.success(response);
    }

    @GetMapping("/by-booking/{bookingId}")
    public ApiResult<PaymentDTO> getPaymentByBookingId(@PathVariable Integer bookingId) {
        Optional<Payment> byBookingRoomId = paymentRepository.findByBookingRoomId(bookingId);

        if (byBookingRoomId.isEmpty()) {
            throw RestException.error("Payment not found for this booking");
        }

        Payment payment = byBookingRoomId.get();
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setBookingRoomId(payment.getBookingRoom().getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());

        return ApiResult.success(paymentDTO);
    }

    @GetMapping
    public ApiResult<List<PaymentDTO>> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentDTO> paymentDTOList = payments.stream().map(payment -> {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setId(payment.getId());
            paymentDTO.setBookingRoomId(payment.getBookingRoom().getId());
            paymentDTO.setAmount(payment.getAmount());
            paymentDTO.setPaymentDate(payment.getPaymentDate());
            paymentDTO.setPaymentMethod(payment.getPaymentMethod());
            return paymentDTO;
        }).collect(Collectors.toList());

        return ApiResult.success(paymentDTOList);
    }
}
