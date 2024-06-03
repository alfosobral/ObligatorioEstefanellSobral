import CSV.Exceptions.InvalidCountry;
import CSV.ManipularCSV;
import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.Hash.Hash;
import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws InterruptedException, EmptyList, InvalidIndex, InvalidCountry, InvalidHashKey {
        boolean programaFuncionando = true;
        System.out.println("Cargando base de datos");
//        for (int i = 0; i < 3; i++) {
//            System.out.println(".");
//            Thread.sleep(1000);
//        }
        ManipularCSV mcvs = new ManipularCSV();
        mcvs.readFile("C:\\Users\\Alfonso\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv");
        Scanner input = new Scanner(System.in);
        System.out.println("Bienvenido al consultor de rankings de Spotify!");
        System.out.println("A continuacion se listan las opciones para realizar consultas:");
        System.out.println();
        System.out.println("1 - Top 10 canciones en un país en un día dado");
        System.out.println("2 - Top 5 canciones que aparecen en más top 50 en un día dado");
        System.out.println("3 - Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado");
        System.out.println("4 - Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada");
        System.out.println("5 - Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas");
        System.out.println("6 - Cerrar programa");
        while (programaFuncionando) {
            System.out.println();
            System.out.println("Por favor, ingrese que opcion desea consultar: ");
            int opcion = (int)(input.nextInt());
            switch (opcion) {
                case 1:
                    boolean opcion1 = true;
                    while (opcion1) {
                        boolean paisValido = false;
                        String pais = null;
                        String fecha = null;
                        while (!paisValido) {
                            System.out.println();
                            System.out.println("Ingrese un pais a continuacion: ");
                            pais = input.next();
                            //if (hashPaises.contains(pais))
                            paisValido = true;
                        }
                        boolean fechaValida = false;
                        while (!fechaValida) {
                            System.out.println();
                            System.out.println("Ingrese una fecha a continuacion (DD/MM/YYYY): ");
                            fecha = input.next();
                            //if (fecha valida)
                            fechaValida= true;
                        }
                        mcvs.opcion1(pais, fecha);
                        }
                    break;
                case 2:
                    boolean opcion2 = true;
                    while (opcion2) {
                        String fecha = null;
                        boolean fechaValida = false;
                        while (!fechaValida) {
                            System.out.println();
                            System.out.println("Ingrese una fecha a continuacion (DD/MM/YYYY): ");
                            fecha = input.next();
                            //if (fecha valida)
                            fechaValida= true;
                        }
                        mcvs.opcion2(fecha);
                    }
                    break;
                case 3:
                    boolean opcion3 = true;
                    while (opcion3) {}
                    break;
                case 4:
                    boolean opcion4 = true;
                    while (opcion4) {}
                    break;
                case 5:
                    boolean opcion5 = true;
                    while (opcion5) {}
                    break;
                case 6:
                    programaFuncionando = false;
                    break;
                default:
                    System.out.println("ERROR! Ingrese un numero del 1 - 6");
                    break;
            }

        }
        System.out.println("Apagando sistema.");
        for (int i = 0; i < 3; i++) {
            System.out.println(".");
            Thread.sleep(1000);
        }
        System.out.println("Finalizado.");

                // Matriz con nombres de países en minúsculas y códigos de país en mayúsculas
        String[][] countries = {
                {"afghanistan", "AF"},
                {"albania", "AL"},
                {"algeria", "DZ"},
                {"andorra", "AD"},
                {"angola", "AO"},
                {"argentina", "AR"},
                {"armenia", "AM"},
                {"australia", "AU"},
                {"austria", "AT"},
                {"azerbaijan", "AZ"},
                {"bahamas", "BS"},
                {"bahrain", "BH"},
                {"bangladesh", "BD"},
                {"barbados", "BB"},
                {"belarus", "BY"},
                {"belgium", "BE"},
                {"belize", "BZ"},
                {"benin", "BJ"},
                {"bhutan", "BT"},
                {"bolivia", "BO"},
                {"bosnia and herzegovina", "BA"},
                {"botswana", "BW"},
                {"brazil", "BR"},
                {"brunei", "BN"},
                {"bulgaria", "BG"},
                {"burkina faso", "BF"},
                {"burundi", "BI"},
                {"cabo verde", "CV"},
                {"cambodia", "KH"},
                {"cameroon", "CM"},
                {"canada", "CA"},
                {"central african republic", "CF"},
                {"chad", "TD"},
                {"chile", "CL"},
                {"china", "CN"},
                {"colombia", "CO"},
                {"comoros", "KM"},
                {"congo", "CG"},
                {"costa rica", "CR"},
                {"croatia", "HR"},
                {"cuba", "CU"},
                {"cyprus", "CY"},
                {"czechia", "CZ"},
                {"democratic republic of the congo", "CD"},
                {"denmark", "DK"},
                {"djibouti", "DJ"},
                {"dominica", "DM"},
                {"dominican republic", "DO"},
                {"ecuador", "EC"},
                {"egypt", "EG"},
                {"el salvador", "SV"},
                {"equatorial guinea", "GQ"},
                {"eritrea", "ER"},
                {"estonia", "EE"},
                {"eswatini", "SZ"},
                {"ethiopia", "ET"},
                {"fiji", "FJ"},
                {"finland", "FI"},
                {"france", "FR"},
                {"gabon", "GA"},
                {"gambia", "GM"},
                {"georgia", "GE"},
                {"germany", "DE"},
                {"ghana", "GH"},
                {"greece", "GR"},
                {"grenada", "GD"},
                {"guatemala", "GT"},
                {"guinea", "GN"},
                {"guinea-bissau", "GW"},
                {"guyana", "GY"},
                {"haiti", "HT"},
                {"honduras", "HN"},
                {"hungary", "HU"},
                {"iceland", "IS"},
                {"india", "IN"},
                {"indonesia", "ID"},
                {"iran", "IR"},
                {"iraq", "IQ"},
                {"ireland", "IE"},
                {"israel", "IL"},
                {"italy", "IT"},
                {"jamaica", "JM"},
                {"japan", "JP"},
                {"jordan", "JO"},
                {"kazakhstan", "KZ"},
                {"kenya", "KE"},
                {"kiribati", "KI"},
                {"kuwait", "KW"},
                {"kyrgyzstan", "KG"},
                {"laos", "LA"},
                {"latvia", "LV"},
                {"lebanon", "LB"},
                {"lesotho", "LS"},
                {"liberia", "LR"},
                {"libya", "LY"},
                {"liechtenstein", "LI"},
                {"lithuania", "LT"},
                {"luxembourg", "LU"},
                {"madagascar", "MG"},
                {"malawi", "MW"},
                {"malaysia", "MY"},
                {"maldives", "MV"},
                {"mali", "ML"},
                {"malta", "MT"},
                {"marshall islands", "MH"},
                {"mauritania", "MR"},
                {"mauritius", "MU"},
                {"mexico", "MX"},
                {"micronesia", "FM"},
                {"moldova", "MD"},
                {"monaco", "MC"},
                {"mongolia", "MN"},
                {"montenegro", "ME"},
                {"morocco", "MA"},
                {"mozambique", "MZ"},
                {"myanmar", "MM"},
                {"namibia", "NA"},
                {"nauru", "NR"},
                {"nepal", "NP"},
                {"netherlands", "NL"},
                {"new zealand", "NZ"},
                {"nicaragua", "NI"},
                {"niger", "NE"},
                {"nigeria", "NG"},
                {"north korea", "KP"},
                {"north macedonia", "MK"},
                {"norway", "NO"},
                {"oman", "OM"},
                {"pakistan", "PK"},
                {"palau", "PW"},
                {"palestine", "PS"},
                {"panama", "PA"},
                {"papua new guinea", "PG"},
                {"paraguay", "PY"},
                {"peru", "PE"},
                {"philippines", "PH"},
                {"poland", "PL"},
                {"portugal", "PT"},
                {"qatar", "QA"},
                {"romania", "RO"},
                {"russia", "RU"},
                {"rwanda", "RW"},
                {"saint kitts and nevis", "KN"},
                {"saint lucia", "LC"},
                {"saint vincent and the grenadines", "VC"},
                {"samoa", "WS"},
                {"san marino", "SM"},
                {"sao tome and principe", "ST"},
                {"saudi arabia", "SA"},
                {"senegal", "SN"},
                {"serbia", "RS"},
                {"seychelles", "SC"},
                {"sierra leone", "SL"},
                {"singapore", "SG"},
                {"slovakia", "SK"},
                {"slovenia", "SI"},
                {"solomon islands", "SB"},
                {"somalia", "SO"},
                {"south africa", "ZA"},
                {"south korea", "KR"},
                {"south sudan", "SS"},
                {"spain", "ES"},
                {"sri lanka", "LK"},
                {"sudan", "SD"},
                {"suriname", "SR"},
                {"sweden", "SE"},
                {"switzerland", "CH"},
                {"syria", "SY"},
                {"taiwan", "TW"},
                {"tajikistan", "TJ"},
                {"tanzania", "TZ"},
                {"thailand", "TH"},
                {"timor-leste", "TL"},
                {"togo", "TG"},
                {"tonga", "TO"},
                {"trinidad and tobago", "TT"},
                {"tunisia", "TN"},
                {"turkey", "TR"},
                {"turkmenistan", "TM"},
                {"tuvalu", "TV"},
                {"uganda", "UG"},
                {"ukraine", "UA"},
                {"united arab emirates", "AE"},
                {"united kingdom", "GB"},
                {"united states", "US"},
                {"uruguay", "UY"},
                {"uzbekistan", "UZ"},
                {"vanuatu", "VU"},
                {"vatican city", "VA"},
                {"venezuela", "VE"},
                {"vietnam", "VN"},
                {"yemen", "YE"},
                {"zambia", "ZM"},
                {"zimbabwe", "ZW"}
        };

            }
        }
