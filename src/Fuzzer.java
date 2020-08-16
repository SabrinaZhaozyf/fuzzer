import java.util.ArrayList;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

// strategy inspirations: https://lcamtuf.blogspot.com/2014/08/binary-fuzzing-strategies-what-works.html
public class Fuzzer {

    private byte[] initialBytes;
    private ArrayList<byte[]> inputSamples = new ArrayList<>();

    public Fuzzer(String fileName) {
        this.initialBytes = Fuzzer.toBytes(fileName);
        this.inputSamples.add(initialBytes);
    }

    public byte[] mutate(byte[] parentSample) {
        byte[] parentCopy = Arrays.copyOf(parentSample, parentSample.length);
        byte[] child;
        int r = new Random().nextInt(4);
        switch (r) {
            case 0: child = mutate_bits(parentCopy);
            case 1: child = mutate_bytes(parentCopy);
            case 2: child = mutate_mag_num(parentCopy);
            case 3: child = mutate_trim(parentCopy);
            default: child = parentCopy;
        }
        return child;
    }

    public byte[] mutate_bits(byte[] parentSample) {

    }

    public byte[] mutate_bytes(byte[] parentSample) {

    }

    public byte[] mutate_mag_num(byte[] parentSample) {

    }

    public byte[] mutate_trim(byte[] parentSample) {

    }


    // load the file, convert content in the file to bytes
    public static byte[] toBytes(String fileName) {
        byte[] inputBytes;
        try {
            inputBytes = Files.readAllBytes(Paths.get(fileName));
        } catch (Exception e) {
            // returns a simple random byte array
            inputBytes = new byte[20];
            new Random().nextBytes(inputBytes);
        }
        return inputBytes;
    }

    // write to new test file (precondition: fileName already exists) with mutated data
    public static void toTest(String fileName, byte[] newSeed) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(new String(newSeed));
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

