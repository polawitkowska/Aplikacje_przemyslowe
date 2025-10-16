package service;

import model.Employee;
import exception.ApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.*;
import model.Position;

public class ApiService {
    public List<Employee> fetchEmployeesFromApi(String apiUrl) throws ApiException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ApiException("Błąd przy pobieraniu odpowiedzi: " + e.getMessage());
        }

        System.out.println("\nKod statusu: " + response.statusCode());
        String jsonResponse = response.body();

        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonResponse, JsonArray.class);
        List<Employee> employees = new ArrayList<>();

        for (JsonElement element : jsonArray) {
            JsonObject employeeObject = element.getAsJsonObject();
            String[] nameParts = employeeObject.get("name").getAsString().split(" ", 2);
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            Employee employee = new Employee(
                    firstName, lastName,
                    employeeObject.get("email").getAsString(),
                    employeeObject.getAsJsonObject("company").get("name").getAsString(),
                    Position.PROGRAMISTA, Position.PROGRAMISTA.getBaseSalary()

            );
            employees.add(employee);
        }

        return employees;
    }
}
