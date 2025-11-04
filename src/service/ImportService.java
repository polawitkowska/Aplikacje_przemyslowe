package service;
import model.Employee;
import model.ImportSummary;
import model.Position;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportService {
    public ImportSummary importFromCsv(String filePath, CompanySystem system) {
        List<String> errors = new ArrayList<>();
        int importedCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            String line;
            int lineNumber = 0;

            reader.readLine(); //pomijam nagłówek

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue; //pomijam puste

                String[] parts = line.split(",");
                if (parts.length != 6) {
                    errors.add("Linia " + lineNumber + ": Nieprawidłowa liczba kolumn.");
                    continue;
                }

                for (int i = 0; i < parts.length; i++) parts[i] = parts[i].trim();

                if (!validatePosition(parts)) {
                    errors.add("Linia " + lineNumber + ": Nieprawidłowe stanowisko: " + parts[4]);
                    continue;
                }

                int salary;
                try {
                    salary = Integer.parseInt(parts[5]);
                    if (salary <= 0) {
                        errors.add("Linia " + lineNumber + ": Wynagrodzenie musi być dodatnie: " + parts[5]);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    errors.add("Linia " + lineNumber + " " + e.getMessage());
                    continue;
                }

                Employee e = new Employee(parts[0], parts[1], parts[2], parts[3],
                        Position.valueOf(parts[4].toUpperCase()), salary);
                system.addEmployee(e);
                importedCount++;
            }
        } catch (IOException e) {
            errors.add("Błąd odczytu pliku: " + e.getMessage());
        }

        return new ImportSummary(importedCount, errors);
    }

    private boolean validatePosition(String[] parts) {
        try {
            Position.valueOf(parts[4].toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
