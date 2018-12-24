package org.mpatapenka.ssp;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Validated
@ConfigurationProperties("ssp")
public class SspProperties {
    /** Email order confirmation will be send to this email list. */
//    @Email
    private List<String> confirmationEmails = Lists.newArrayList();

    /** Root directory to put all loaded images. */
    @NotEmpty
    private String imageStorageLocation;

    /** Price subfolder for different types of prices. */
    private DeliveryPrice deliveryPrices = new DeliveryPrice();

    @Getter
    @Setter
    public static class DeliveryPrice {
        /** Cash on delivery. */
        @NotNull
        private BigDecimal cashOnDelivery;
    }
}