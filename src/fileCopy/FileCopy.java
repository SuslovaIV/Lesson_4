package fileCopy;
import java.io.*;

public class FileCopy {
    private static volatile double bites;
    private static volatile double size;

    public static void main(String[] args) throws IOException {
        String fileName = "test.png";
        System.out.println("Текущий каталог: " + System.getProperty("user.dir"));
        System.out.println("Используем буфер");
        testCopyBuffer(fileName, 200);
        testCopyBuffer(fileName,300);
        testCopy(fileName);
    }
    public static void testCopyBuffer(String myFile, int sizeBuffer) throws IOException {
        var t1 = System.currentTimeMillis();
        var t2 = System.currentTimeMillis();
        var file = new File(myFile);
        int s = sizeBuffer;
        bites = 0;
        size = file.length();
        t1 = System.currentTimeMillis();
        Thread thread = getStatusThread();
        thread.start();

        try(var bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            var bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(myFile + "_копия.pdf"))) {

            var buffer = new byte[sizeBuffer];
            while ((sizeBuffer = bufferedInputStream.read(buffer, 0, buffer.length))  > 0) {
                bites += sizeBuffer;
                bufferedOutputStream.write(buffer, 0, sizeBuffer);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        thread.interrupt();
        t2 = System.currentTimeMillis();
        System.out.println("Для буфера размером " + s + " время = " + (t2 - t1));
    }
    public static void testCopy(String myFile) throws IOException{
        var t1 = System.currentTimeMillis();
        var t2 = System.currentTimeMillis();
        var file = new File(myFile);
        System.out.println("Без буфера");
        bites = 0;
        size = file.length();
        t1 = System.currentTimeMillis();
        Thread thread = getStatusThread();
        thread.start();
        try (var inputStream = new FileInputStream(file);
             var outputStream = new FileOutputStream(myFile + "_копия.pdf")) {
            int readBite = -1;
            while ((readBite = inputStream.read()) != -1) {
                bites += 1;
                outputStream.write(readBite);
            }
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
        thread.interrupt();
        t2 = System.currentTimeMillis();
        System.out.println("Без использования буфера время копирования " + (t2 - t1));
    }
    private static Thread getStatusThread() {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(2000);
                    System.out.println("Процент выполнения - " + (bites / size) * 100);
                } catch (InterruptedException ex) {
                   System.out.println("Процент выполнения - " + (bites / size) * 100);
                   Thread.currentThread().interrupt();
                }
            }
        });
        return thread;
    }
}
