package org.mpatapenka.secp;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.mpatapenka.secp.validation.constraint.EmailList;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Validated
@ConfigurationProperties("secp")
public class SecpProperties {
    /**
     * Email order confirmation will be send to this email list.
     */
    @EmailList
    private List<String> confirmationEmails = Lists.newArrayList();

    /**
     * Root directory to put all loaded images.
     */
    @NotEmpty
    private String imageStorageLocation;

    /**
     * Price sub category for different types of prices.
     */
    @Valid
    private DeliveryPrice deliveryPrices = new DeliveryPrice();

    /**
     * Contacts sub category.
     */
    @Valid
    private Contacts contacts = new Contacts();



    @Getter
    @Setter
    public static class Contacts {
        /**
         * Application name.
         */
        @NotEmpty
        private String appName;

        /**
         * Email to contacts.
         */
        @Email
        private String email;

        /**
         * Site to contacts.
         */
        @URL
        private String siteUrl;
    }

    @Getter
    @Setter
    public static class DeliveryPrice {
        /**
         * Cash on delivery.
         */
        @NotNull
        private BigDecimal cashOnDelivery;
    }
}