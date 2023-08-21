import java.util.ArrayList;

public class FileContent {
    boolean IS_EXPENSE;
    int count;
    int price;
    int amount;
    int month;
    String option;

    FileContent(String option, boolean is_expense, int count, int price){
        this.option = option;
        this.IS_EXPENSE = is_expense;
        this.count = count;
        this.price = price;
    }

    FileContent(int month, int amount, boolean is_expense){
        this.month = month;
        this.amount = amount;
        this.IS_EXPENSE = is_expense;
    }

    FileContent(){

    }

    void PrintContentMonth(FileContent content){
        System.out.println(content.option + " " + content.IS_EXPENSE + " " + content.count + " " + content.price);
    }

    void PrintContentYear(FileContent content){
        System.out.println(content.month + " " + content.amount + " " + content.IS_EXPENSE);
    }


    int SumByMonth(ArrayList<FileContent> file, boolean expenseFlag){
        int sum = 0;
        for (int i = 0; i < file.size(); i++) {
            if(file.get(i).IS_EXPENSE == expenseFlag){
                sum += file.get(i).count * file.get(i).price;
            }
        }

        return sum;
    }

    FileContent MaxByMonth(ArrayList<FileContent> file, boolean expenseFlag){
        FileContent max = new FileContent();
        max.amount = 0;
        for(FileContent fileString : file){
            if (fileString.IS_EXPENSE == expenseFlag && fileString.count*fileString.price > max.amount){
                max = fileString;
                max.amount = fileString.count*fileString.price;
            }
        }
        return max;
    }

    int AvgByMonth(ArrayList<FileContent> file, boolean expenseFlag){
        int count = file.size();
        return SumByMonth(file,expenseFlag)/count;
    }

    FileContent MinByMonth(ArrayList<FileContent> file, boolean expenseFlag){
        FileContent min = MaxByMonth(file, expenseFlag);
        for(FileContent fileString : file){
            if(fileString.IS_EXPENSE == expenseFlag && fileString.price * fileString.count<min.amount){
                min = fileString;
                min.amount = fileString.count* fileString.price;
            }
        }
        return min;
    }

    FileContent MaxByYear(ArrayList<FileContent> file, boolean expenseFlag){
        FileContent max = new FileContent();
        for(FileContent fileString : file){
            if(fileString.IS_EXPENSE == expenseFlag && fileString.amount > max.amount){
               //max = fileString;
                max.amount = fileString.amount;
                max.month = fileString.month;
                max.IS_EXPENSE = fileString.IS_EXPENSE;
            }
        }
        return max;
    }


    int SumByYear(ArrayList<FileContent> file, boolean expenseFlag){
        int sum = 0;
        for(FileContent fileString : file){
            if(fileString.IS_EXPENSE == expenseFlag){
                sum += fileString.amount;
            }

        }
        return sum;
    }

    int SumByMonthInYear(ArrayList<FileContent> file,int month, boolean expenseFlag){
        int sum = 0;
        for(FileContent filestring : file){
            if(filestring.month == month && filestring.IS_EXPENSE==expenseFlag){
                sum = filestring.amount;
            }
        }
        return sum;
    }

    int AvgByYear(ArrayList<FileContent> file, boolean expenseFlag){
        return SumByYear(file, expenseFlag)/file.size();
    }

    FileContent MinByYear(ArrayList<FileContent> file, boolean expenseFlag){
        FileContent minYear = new FileContent();
        minYear.amount = MaxByYear(file, expenseFlag).amount;
        for(FileContent fileString : file){
            if(fileString.IS_EXPENSE == expenseFlag && fileString.amount < minYear.amount){
                minYear = fileString;
            }
        }
        return minYear;
    }

    String MonthFromNum(int number){
        return switch (number) {
            case 0 -> "Январь";
            case 1 -> "Февраль";
            case 2 -> "Март";
            case 3 -> "Апрель";
            case 4 -> "Май";
            case 5 -> "Июнь";
            case 6 -> "Июль";
            case 7 -> "Август";
            case 8 -> "Сентябрь";
            case 9 -> "Октябрь";
            case 10 -> "Ноябрь";
            case 11 -> "Декабрь";
            default -> null;
        };

    }

    void PrintMonthStatistics(ArrayList<FileContent> file){
        FileContent content = new FileContent();
        System.out.println("Сумма доходов составляет: " + content.SumByMonth(file, false));
        System.out.println("Сумма расходов составляет: " + content.SumByMonth(file, true));
        System.out.println("Минимальная статья доходов - " + content.MinByMonth(file,false).option + ". Доход : " + content.MinByMonth(file,false).amount);
        System.out.println("Минимальная статья расходов - " + content.MinByMonth(file,true).option + ". Доход : " + content.MinByMonth(file,true).amount);
        System.out.println("Максимальная статья доходов - " + content.MaxByMonth(file,false).option + ". Доход : " + content.MaxByMonth(file,false).amount);
        System.out.println("Максимальная статья расходов - " + content.MaxByMonth(file,true).option + ". Доход : " + content.MaxByMonth(file,true).amount);
        System.out.println("Среднее значение доходов составляет: " + content.AvgByMonth(file, false));
        System.out.println("Среднее значение расходов составляет: " + content.AvgByMonth(file, true));
    }

    void PrintYearsStatics(ArrayList<FileContent> file){
        FileContent content = new FileContent();
        System.out.println("Сумма доходов составляет: " + content.SumByYear(file, false));
        System.out.println("Сумма расходов составляет: " + content.SumByYear(file, true));
        System.out.println("Средний доход составляет: " + content.AvgByYear(file, false));
        System.out.println("Средний расход составляет: " + content.AvgByYear(file,true));
        System.out.println("Наиболее прибыльным месяцем был " + MonthFromNum(content.MaxByYear(file, false).month-1) + ", прибыль составила " + content.MaxByYear(file, false).amount);
        System.out.println("Наиболее затратный месяц:  " + MonthFromNum(content.MaxByYear(file, true).month-1) + ", расход составил " + content.MaxByYear(file,true).amount);
        System.out.println("Наименее прибыльным месяцем был " + MonthFromNum(content.MinByYear(file, false).month-1) + ", прибыль составила " + content.MinByYear(file,false).amount);
        System.out.println("Наименее затратным был  " + MonthFromNum(content.MinByYear(file, true).month-1) + ", расход составил " + content.MinByYear(file,true).amount);
    }
}
