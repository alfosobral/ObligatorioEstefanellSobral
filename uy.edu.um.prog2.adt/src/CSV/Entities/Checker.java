package CSV.Entities;

import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.Hash.Hash;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Checker {

    private  String[] codPaises = {"GLB", "ZA", "VN", "VE", "UY", "US", "UA", "TW", "TR", "TH", "SV", "SK", "SG", "SE", "SA", "RO", "PY", "PT", "PL", "PK", "PH", "PE", "PA", "NZ", "NO",
            "NL", "NI", "NG", "MY", "MX", "MA", "LV", "LU", "LT", "KZ", "KR", "JP", "IT", "IS", "IN", "IL", "IE", "ID", "HU", "HN", "HK", "GT", "GR", "GB", "FR", "FI", "ES", "EG", "EE",
            "EC", "DO", "DK", "DE", "CZ", "CR", "CO", "CL", "CH", "CA", "BY", "BR", "BO", "BG", "BE", "AU", "AT", "AR", "AE"};

    private String[] nombresPaises = {"global", "sudafrica", "vietnam", "venezuela", "uruguay", "uestados unidos", "ucrania", "taiwan", "turquia", "tailandia", "el salvador", "slovakia",
            "singapur", "suecia", "arabia saudita", "romania", "paraguay", "portugal", "polonia", "pakistan", "filipinas", "peru", "panama", "nueva zelanda", "noruega",
            "paises bajos", "nicaragua", "nigeria", "malaysia", "mexico", "marruecos", "latvia", "luxembourg", "lituania", "kazakhstan", "corea del sur", "japon", "italia",
            "islandia", "india", "israel", "irlanda", "indonesia", "hungria", "honduras", "hong kong", "guatemala", "grecia", "reino unido", "francia", "finlandia",
            "españa", "egito", "estonia", "ecuador", "republica dominicana", "dinamarca", "alemania", "republica checa", "costa rica", "colombia", "chile", "suiza",
            "canada", "bielorrusia", "brasil", "bolivia", "bulgaria", "belgica", "australia", "austria", "argentina", "emiratos arabes"};

    public String checkCountry(String country) throws InvalidHashKey {

        country = country.toLowerCase();
        Hash<String, String> hashPaises = new Hash<>(5);

        for (int i = 0; i < codPaises.length; i++) {
            hashPaises.add(nombresPaises[i], codPaises[i]);
        }

        if (!hashPaises.contains(country)) {
            return null;
        } else {
            return hashPaises.search(country);
        }

    }

    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Formato de fecha inválido: " + e.getMessage());
        }
        return date;
    }

    public boolean checkDate(String date) {
        if (date.length() == 10){
            for (int i = 0; i < date.length(); i++) {
                char ch = date.charAt(i);
                switch (i){
                    case 0,1,2,3,5,6,8,9:
                        if (!Character.isDigit(ch)){
                            System.out.println("Fecha inválida: " + date);
                            return false;
                        }
                        break;
                    case 4,7:
                        if (ch != '-') {
                            System.out.println("Fecha inválida: " + date);
                            return false;
                        }
                        break;

                }
            }
        }else {
            System.out.println("Fecha inválida: " + date);
            return false;
        }
        Date dateD = convertStringToDate(date);
        if (dateD.after(convertStringToDate("2023-10-17")) && dateD.before(convertStringToDate("2024-05-14"))) {
            if(!dateD.equals(convertStringToDate("2024-04-24"))) {
                return true;
            }
            System.out.println("Fecha inválida: " + date);
            return false;
        }
        System.out.println("Fecha inválida: " + date);
        return false;
    }
}
