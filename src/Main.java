import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!

        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<FileContent>> allMonths = null;
        ArrayList<ArrayList<FileContent>> allYears = null;
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        boolean mothsHere = false;
        boolean yearsHere = false;
        FileContent content = new FileContent();
        while (true){
            printMenu();
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    allMonths = monthlyReport.ReadMonths();
                    mothsHere = true;
                    for (int i = 0; i < allMonths.size(); i++) {
                        content.MonthFromNum(i);
                        for(int j = 0; j < allMonths.get(i).size(); j++){
                            content.PrintContentMonth(allMonths.get(i).get(j));
                        }
                    }
                    break;
                case 2:
                    allYears = yearlyReport.ReadYears();
                    yearsHere = true;
                    for (int i = 0; i < allYears.size(); i++) {
                        System.out.print("Год №");
                        System.out.println(i+2021);
                        for(int j = 0; j < allYears.get(i).size(); j++){
                            allYears.get(i).get(j).PrintContentYear(allYears.get(i).get(j));
                        }
                    }
                    break;
                case 3:
                    if(!(mothsHere&&yearsHere)){
                        if(!mothsHere){
                            System.out.println("Загрузка месячных отчетов");
                            allMonths = monthlyReport.ReadMonths();
                        }
                        if(!yearsHere){
                            System.out.println("Загрузка годового отчета");
                            allYears = yearlyReport.ReadYears();
                        }
                    }
                    boolean dataIsOk = true;
                    for(int i = 0; i < allMonths.size(); i++){  // Тут я получаю номер месяца
                        int j = i/12;
                        if(content.SumByMonth(allMonths.get(i), true) != content.SumByMonthInYear(allYears.get(j),i+1,true)){
                            dataIsOk = false;
                        }
                        if(content.SumByMonth(allMonths.get(i), false) != content.SumByMonthInYear(allYears.get(j), i + 1, false)){
                            dataIsOk = false;
                        }
                        if(!dataIsOk){
                            int monthNumber = i+1;
                            System.out.println("Есть расхождение в месяце №" + monthNumber);
                        }
                        else {
                            System.out.println("Отчеты прошли проврку, ошибок нет");
                        }
                    }
                    break;
                case 4:
                    if(!mothsHere || !yearsHere){
                        allMonths = monthlyReport.ReadMonths();
                        allYears = yearlyReport.ReadYears();
                    }
                        for(int i = 0; i < allMonths.size(); i++){
                            System.out.println(content.MonthFromNum(i));
                            content.PrintMonthStatistics(allMonths.get(i));
                        }
                    break;
                case 5:
                    if(!mothsHere || !yearsHere){
                        allMonths = monthlyReport.ReadMonths();
                        allYears = yearlyReport.ReadYears();
                    }
                    for(int i = 0; i < allYears.size(); i++){
                        content.PrintYearsStatics(allYears.get(i));
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Такой команды нет, повторите ввод");
            }
        }
    }

    static void printMenu(){
        System.out.println("Выберите действие");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты ");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из программы");
    }
}

