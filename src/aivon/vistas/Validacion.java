/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.vistas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author MArio
 */
public class Validacion {
    private static final String EMAIL_PATTERN = "[A-Za-z0-9_-]+([A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(a?.[A-Za-z0-9-]+)*([A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValid(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
