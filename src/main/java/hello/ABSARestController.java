package hello;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.web.bind.annotation.*;

@RestController
public class ABSARestController {

    @GetMapping("/determineDestination")
    public String determineDestination() {
        return "ROUTING_RULES_SERVICE";
    }

    @PostMapping("/retrieveFailedPaymentMessageStatus")
    public String retrieveFailedPaymentMessageStatus() {
        return "FAILED";
    }

    @PostMapping("/retrieveSuccessfulPaymentMessageStatus")
    public String retrieveSuccessfulPaymentMessageStatus(@RequestBody String paymentXml) {
        Document doc = Jsoup.parse(paymentXml, "", Parser.xmlParser());
        for (Element e : doc.select("logicalTerminal")) {
            if(e.hasText()){
                System.out.println(e.text());
                return "SUCCESS";
            }
        }
        return "FAILED";
    }

    @PostMapping("/sendValidatedPaymentMessage")
    public String sendValidatedPaymentMessage() {
        return "VALID";
    }
}
