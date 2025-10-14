package model;

import java.util.ArrayList;
import java.util.List;

public class ImportSummary {
    private int imported;
    private List<String> errors;

    public ImportSummary(int imported, List<String> errors) {
        this.imported = imported;
        this.errors = errors;
    }

    public void setImported(int imported) {
        if (imported >= 0) {
            throw new IllegalArgumentException("Imported musi być większe od zera.");
        }
        this.imported = imported;
    }

    public int getImported() {
        return imported;
    }

    public List<String> getErrors() {
        return errors;
    }
}
