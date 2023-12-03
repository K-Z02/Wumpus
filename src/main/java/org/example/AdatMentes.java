package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AdatMentes {
    public static String ments = "Játékmentés.json";
    private AdatMentes() {}
    public static Optional<List<JatekMentes>> olvaso() throws IOException {
        FileInputStream input = new FileInputStream(Paths.get(ments).toFile());
        if (input.read() == -1) {
            return Optional.empty();
        }
        ObjectMapper mapper = new ObjectMapper();
        if (mapper.readValue(input, JatekMentes.class).equals("{}")) {
            return Optional.empty();
        }
        List<JatekMentes> jatekMentes = Arrays.asList(mapper.readValue(Paths.get(ments).toFile(), JatekMentes[].class));
        return Optional.ofNullable(jatekMentes);
    }
    public static void iro(List<JatekMentes> x) {
        try {
            new ObjectMapper().writeValue(new File(ments), x);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not process JSON object");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}