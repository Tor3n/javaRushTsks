
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JRFiles {

    public static void main(String[] args) {

        File directory = new File(args[0]);
        File allFilesFile = new File(args[1]);
        File allFilesFileFIN=null;
        if (FileUtils.isExist(allFilesFile)){
            String[] resSpl = args[1].split("/");
            int lng = resSpl[resSpl.length-1].length();
            String fin = String.copyValueOf(allFilesFile.toString().toCharArray(),0,allFilesFile.toString().length()-lng);
            allFilesFileFIN = new File(fin+"/"+"allFilesContent.txt");
            FileUtils.renameFile(allFilesFile, allFilesFileFIN);
        }

        HashMap<File, Object> fileCat = new HashMap<>();
        fileCat = fillTheCat(directory);
        if(FileUtils.isExist(allFilesFileFIN)){
            try{
                fillallFilesContent(allFilesFileFIN, fileCat);
            } catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    public static HashMap fillTheCat(File fls){
        File[] allSubCat = fls.listFiles();
        HashMap<File, Object> result= new HashMap<>();

        for (File f: allSubCat) {
            if(f.isFile()){
                result.put(f, new Integer(0));
            } else {
                result.put(f, fillTheCat(f));
            }
        }

        return result;
    }

    public static void fillallFilesContent(File file, HashMap<File,Object> hm) throws Exception {
        FileWriter writer = new FileWriter(file);
        StringBuilder result = new StringBuilder();
        fillSTRB(hm,result);
        writer.write(result.toString());
        //System.out.println(result.toString());
        writer.close();
    }

    public static void fillSTRB(HashMap<File,Object> hm, StringBuilder strb) throws Exception{

        for(Map.Entry<File, Object> entry : hm.entrySet()) {
            File key = entry.getKey();
            Object value = entry.getValue();
            if(key.isFile()){
                if(key.length()<=50){
                    FileReader reader = new FileReader(key);
                    char [] a = new char[50];
                    reader.read(a);
                    strb.append(String.valueOf(a)+"\n");
                    reader.close();
                }
            }else{
                fillSTRB((HashMap<File, Object>) value,strb);
            }
        }
    }

}
