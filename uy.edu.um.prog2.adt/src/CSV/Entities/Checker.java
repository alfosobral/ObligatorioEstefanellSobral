package CSV.Entities;

import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.Hash.Hash;

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
}
