package by.inlove.shop;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import by.inlove.shop.validation.constraint.EmailList;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * InLove application properties.
 */
@Getter
@Setter
@Validated
@ConfigurationProperties("in-love")
public class InLoveProperties {
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
     * Price sub category for different types of delivery prices.
     */
    @Valid
    private DeliveryPrice deliveryPrices;

    /**
     * Application meta information.
     */
    @Valid
    private MetaInfo metaInfo;



    /**
     * Application meta information.
     */
    @Getter
    @Setter
    public static class MetaInfo {
        /**
         * Application title.
         */
        @NotEmpty
        private String title;

        /**
         * Application description.
         */
        @NotEmpty
        private String description;

        /**
         * Application API version.
         */
        @NotEmpty
        private String version;

        /**
         * Application owners contacts information.
         */
        @Valid
        private Contacts contacts;
    }

    /**
     * Application owner contacts information.
     */
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

    /**
     * Delivery prices.
     */
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