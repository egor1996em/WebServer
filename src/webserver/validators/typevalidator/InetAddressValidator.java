
package webserver.validators.typevalidator;

import java.io.Serializable;
/**
 *
 * @author User
 * Проверка правильности ввода IP адреса
 */
public class InetAddressValidator {

    private static final String IPV4_REGEX =
            "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";

   
    private static final InetAddressValidator VALIDATOR = new InetAddressValidator();

    /** IPv4 RegexValidator */
    private final RegexValidator ipv4Validator = new RegexValidator(IPV4_REGEX);


    public static InetAddressValidator getInstance() {
        return VALIDATOR;
    }


    public boolean isValid(String inetAddress) {
        return isValidInet4Address(inetAddress);
    }

    public boolean isValidInet4Address(String inet4Address) {
        // verify that address conforms to generic IPv4 format
        String[] groups = ipv4Validator.match(inet4Address);

        if (groups == null) {
            return false;
        }

        // verify that address subgroups are legal
        for (int i = 0; i <= 3; i++) {
            String ipSegment = groups[i];
            if (ipSegment == null || ipSegment.length() <= 0) {
                return false;
            }

            int iIpSegment = 0;

            try {
                iIpSegment = Integer.parseInt(ipSegment);
            } catch(NumberFormatException e) {
                return false;
            }

            if (iIpSegment > 255) {
                return false;
            }

        }

        return true;
    }
}
