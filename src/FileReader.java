import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {


    ArrayList<String> readFileContents(String fileName) {           // получает название файла и выводит его содержимое в лист
        String path = "./resources/" + fileName;
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }
    }

    ArrayList<String> GetFileNames(List<File> FileList, String mask){  // получает сет файлов и маску по которой определяет тип документа
        Pattern pattern = Pattern.compile(mask);                         // и возвращает список названий файлов по типу
        ArrayList<String> FileNames = new ArrayList<>();
        for(File file : FileList){
            Matcher matcher = pattern.matcher(file.getName());
            if(matcher.find()){
                FileNames.add(file.getName());
            }
        }
        return FileNames;
    }

    FileContent GetFileContent(String StringFile){
        FileContent content;
        if(Character.isDigit(StringFile.charAt(0))){
            String[] buffer = StringFile.split(",", 3);
            content = new FileContent((Integer.parseInt(buffer[0])), Integer.parseInt(buffer[1]), buffer[2].trim().toLowerCase().contains("true"));
        } else{
            String[] buffer = StringFile.split(",", 4);
            content = new FileContent(buffer[0], buffer[1].trim().toLowerCase().contains("true"), Integer.parseInt(buffer[2]), Integer.parseInt(buffer[3]));
        }
        return content;
    }


}
