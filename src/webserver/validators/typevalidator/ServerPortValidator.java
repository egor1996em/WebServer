package webserver.validators.typevalidator;


/**
 *
 * @author User
 * Проверка правильности ввода порта
 */
public class ServerPortValidator {
    
    private static final ServerPortValidator VALIDATOR = new ServerPortValidator();
    
    public static ServerPortValidator getInstance() {
        return VALIDATOR;
    }
    //Проверим, что введен правильный порт
    public boolean isValid(String PortText) {
        int Port;
        try {
            Port = Integer.parseInt(PortText);
            if (Port < 1025 || Port > 65535) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
