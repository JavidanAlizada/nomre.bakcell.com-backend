package com.bakcell.nomre.controller;

import com.bakcell.nomre.enums.Category;
import com.bakcell.nomre.enums.Status;
import com.bakcell.nomre.model.request.PhoneNumberRequest;
import com.bakcell.nomre.model.request.ReserveRequest;
import com.bakcell.nomre.model.response.GetAllReservations;
import com.bakcell.nomre.model.response.PhoneNumberResponse;
import com.bakcell.nomre.model.response.ReservationResponse;
import com.bakcell.nomre.service.PhoneNumbersService;
import com.bakcell.nomre.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.lang.String.format;


/**
 * @author Javidan Alizada
 */
@Api(tags = "freemsisdn-nomre")
@PropertySource("classpath:messages.properties")
@RequestMapping("/api/v1/freemsisdn-nomre")
@Slf4j
@RequiredArgsConstructor
@RestController
public class FreeMSISDNController {

    private final PhoneNumbersService phoneNumbersService;
    private final ReservationService reservationService;

    @GetMapping
    @ApiOperation(value = "${api.freemsisdn-nomre.controller.findAll}")
    public ResponseEntity<PhoneNumberResponse> findAll() {
        log.info("Searching list of MSISDN....");
        return new ResponseEntity<>(this.phoneNumbersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    @ApiOperation(value = "${api.freemsisdn-nomre.controller.search}")
    public ResponseEntity<PhoneNumberResponse> search(@RequestParam(value = "prefix", required = false) String prefix,
                                                      @RequestParam(value = "msisdn", required = false) String msisdn,
                                                      @RequestParam(value = "category", required = false) Category category) {
        log.info(format("search by query: %s, %s, %s", prefix, msisdn, category));
        PhoneNumberRequest request = new PhoneNumberRequest(prefix, msisdn, category);
        return new ResponseEntity<>(this.phoneNumbersService.findByQuery(request), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value = "${api.freemsisdn-nomre.controller.delete.all}")
    public ResponseEntity<Void> deleteAllPhoneNumbers() {
        log.warn("All MSISDN deleting....");
        this.phoneNumbersService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/reserve")
    @ApiOperation(value = "${api.freemsisdn-nomre.controller.reserve}")
    public ResponseEntity<ReservationResponse> reserve(@ApiParam("ReserveRequest") @Valid @RequestBody ReserveRequest reserveRequest) {
        log.info("reserve msisdn");
        ReservationResponse response = this.reservationService.reserve(reserveRequest);
        return new ResponseEntity<>(response, response.getStatus().equals(Status.SUCCESS) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/reservations")
    @ApiOperation(value = "${api.freemsisdn-nomre.controller.reservations.delete}")
    public ResponseEntity<Void> deleteAllReservations() {
        log.warn("All Reservations deleting....");
        this.reservationService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/reservations")
    @ApiOperation(value = "${api.freemsisdn-nomre.controller.get}")
    public ResponseEntity<GetAllReservations> getAllReservations() {
        log.info("GET ALL RESERVATIONS");
        GetAllReservations getAllReservations = GetAllReservations.builder()
                .reservationEntities(reservationService.findAll()).build();
        return new ResponseEntity<>(getAllReservations, HttpStatus.OK);
    }
}
