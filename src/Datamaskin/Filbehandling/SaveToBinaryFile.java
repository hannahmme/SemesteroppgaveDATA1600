package Datamaskin.Filbehandling;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class SaveToBinaryFile {

    final static String fileName = "C:\\Temp\\allProducts.dat";

    byte[] readBinaryFile (String fileName) throws  IOException{
        Path path = Paths.get(fileName);
        return Files.readAllBytes(path);
    }

    void writeBinaryFile (byte [] bytes, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Files.write(path, bytes);
    }

    private static void log(Object msg){
        System.out.println(String.valueOf(msg));
    }





    public static byte[] readFile (String fileName){
        File file = new File(fileName);
        byte[] result = null;
        try{
            InputStream input =  new BufferedInputStream(new FileInputStream(file));
            result = readAndClose(input);
        }
        catch (FileNotFoundException e){
            log(e);
        }
        return result;
    }

    public static byte[] readAndClose(InputStream input){
        //carries the data from input to output :
        byte[] bucket = new byte[32*1024];
        ByteArrayOutputStream result = null;
        try  {
            try {
                //Use buffering? No. Buffering avoids costly access to disk or network;
                //buffering to an in-memory stream makes no sense.
                result = new ByteArrayOutputStream(bucket.length);
                int bytesRead = 0;
                while(bytesRead != -1){
                    //aInput.read() returns -1, 0, or more :
                    bytesRead = input.read(bucket);
                    if(bytesRead > 0){
                        result.write(bucket, 0, bytesRead);
                    }
                }
            }
            finally {
                input.close();
                //result.close(); this is a no-operation for ByteArrayOutputStream
            }
        }
        catch (IOException ex){
            log(ex);
        }
        return result.toByteArray();
    }

}
