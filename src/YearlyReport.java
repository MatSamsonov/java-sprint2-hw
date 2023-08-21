import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YearlyReport {

    FileReader reader = new FileReader();

     ArrayList<String> GetFileNames() {                                           // получает названия файлов ихсодя из маски
        File dir = new File("./resources"); //path указывает на директорию
        List<File> lst = Arrays.asList(dir.listFiles());
        ArrayList<String>  FileNames = reader.GetFileNames(lst, "y.*");
        return FileNames;
    }

    ArrayList<ArrayList<FileContent>> ReadYears(){
        ArrayList<ArrayList<FileContent>> years = new ArrayList<>();
        ArrayList<String> namesOfMonthReports = GetFileNames();
        for (int i = 0; i < namesOfMonthReports.size(); i++) {
            ArrayList<String> year = reader.readFileContents(namesOfMonthReports.get(i));  // запихиваю в лист строк его содержимое
            ArrayList<FileContent> buffer = new ArrayList<>();   // создаю буферный лист нужного типа данных
            years.add(buffer);                            //  и добавляю этот буфер в лист листов
            for (int j = 1; j < year.size(); j++) {
                years.get(i).add(reader.GetFileContent(year.get(j)));
            }
        }
        return years;
    }

}
