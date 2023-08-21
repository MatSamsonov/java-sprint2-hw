import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class MonthlyReport {

    /*
    Этот лист листов является двумерным массивом, который можно обойти двумя циклами.
    */

    FileReader reader = new FileReader();

    ArrayList<String> GetFileNames() {                              // получает список названий документов по соответствующей маске
        File dir = new File("./resources"); //path указывает на директорию
        ArrayList<String>  FileNames =reader.GetFileNames(Arrays.asList(dir.listFiles()), "m.*");
        return FileNames;
    }

    ArrayList<ArrayList<FileContent>> ReadMonths(){
        ArrayList<ArrayList<FileContent>> months = new ArrayList<>();
        ArrayList<String> namesOfMonthReports = GetFileNames();
        for (int i = 0; i < namesOfMonthReports.size(); i++) {
            ArrayList<String> month = reader.readFileContents(namesOfMonthReports.get(i));  // запихиваю в лист строк его содержимое
            ArrayList<FileContent> buffer = new ArrayList<>();   // создаю буферный лист нужного типа данных
            months.add(buffer);                            //  и добавляю этот буфер в лист листов
            for (int j = 1; j < month.size(); j++) {
                months.get(i).add(reader.GetFileContent(month.get(j)));
            }
        }
        return months;
    }

}
