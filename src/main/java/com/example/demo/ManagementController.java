package com.example.demo;

import com.example.service.PaymentService;
import com.example.user.PaymentScheme;
import com.example.user.Student;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
public class ManagementController {

    private final PaymentService paymentService;

    public ManagementController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // return list fo employees when exposed to "/employees"
    @GetMapping("/payments")
    public List<PaymentScheme> findAll(){
        return paymentService.findAll();
    }


    // add new employee : POST request
    @PostMapping("/payments")
    public PaymentScheme createPaymentScheme(@RequestBody PaymentScheme paymentScheme){   // employee data is coming through the request body in json format-) that why @RequestBody

        // if id!= 0, then this will be treated as an update.
        // to avoid this,initially set the employee id to zero.
        // then it'll force to save a new item instead of update
        paymentScheme.setSchemeId(0);
        return paymentService.save(paymentScheme);
    }



























//    @Operation(
//            description = "Get endpoint for manager",
//            summary = "This is a summary for management get endpoint",
//            responses = {
//                    @ApiResponse(
//                            description = "Success",
//                            responseCode = "200"
//                    ),
//                    @ApiResponse(
//                            description = "Unauthorized / Invalid Token",
//                            responseCode = "403"
//                    )
//            }
//
//    )
//    @GetMapping
//    public String get() {
//        return "GET:: management controller";
//    }
//    @PostMapping
//    public String post() {
//        return "POST:: management controller";
//    }
//    @PutMapping
//    public String put() {
//        return "PUT:: management controller";
//    }
//    @DeleteMapping
//    public String delete() {
//        return "DELETE:: management controller";
//    }
}
