package spring_information_portal.tool;

public class DateTranslator {


    public static String toTimeAgo(long diff){

        if (diff < 60){
            if (diff > 10 && diff < 20)
                return diff + " секунд";
            switch ((int) (diff % 10)){
                case (1):
                    return diff + " секунду";
                case (2):
                case (3):
                case (4):
                    return diff + " секунды";
                default:
                    return diff + " секунд";
            }
        }

        diff /= 60;
        if (diff < 60){
            if (diff > 10 && diff < 20)
                return diff + " минут";
            switch ((int) (diff % 10)){

                case (1):
                return diff + " минуту";
                case (2):
                case (3):
                case (4):
                    return diff + " минуты";
                default:
                    return diff + " минут";
            }
        }
        diff /= 60;
        if (diff < 24){
            if (diff > 10 && diff < 20)
                return diff + " часов";
            switch ((int) (diff % 10)){

                case (1):
                return diff + " час";
                case (2):
                case (3):
                case (4):
                    return diff + " часа";
                default:
                    return diff + " часов";
            }
        }
        diff /= 24;

        if (diff > 10 && diff < 20)
            return diff + " дней";
        switch ((int) (diff % 10)){
            case (1):
                return diff + " день";
            case (2):
            case (3):
            case (4):
                return diff + " дня";
            default:
                return diff + " дней";
        }


    }

}
