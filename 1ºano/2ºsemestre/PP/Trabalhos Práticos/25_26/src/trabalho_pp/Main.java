package trabalho_pp;

import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.pickingManagement.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;
import trabalho_pp.core.*;
import trabalho_pp.io.ImporterImpl;
import trabalho_pp.pickingManagement.*;

public class Main {
    private static Institution institution;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        institution = new InstitutionImpl("Ajuda Humanitaria Nacional");
        
        // Import initial JSON data
        try {
            System.out.println("A carregar dados dos ficheiros JSON...");
            ImporterImpl importer = new ImporterImpl();
            importer.importData(institution);
            System.out.println("Dados importados com sucesso! AidBoxes carregadas: " + institution.getAidBoxes().length);
        } catch (IOException | InstitutionException e) {
            System.out.println("Aviso: Nao foi possivel carregar dados JSON (" + e.getMessage() + ").");
            System.out.println("A iniciar com base de dados vazia.");
        }

        // Add some default vehicles to start with
        try {
            institution.addVehicle(new NormalVehicleImpl("V-STANDARD-1", ItemType.CLOTHING, 800));
            institution.addVehicle(new NormalVehicleImpl("V-STANDARD-2", ItemType.MEDICINE, 500));
            institution.addVehicle(new NormalVehicleImpl("V-STANDARD-3", ItemType.NON_PERISHABLE_FOOD, 1000));
            institution.addVehicle(new RefrigeratedVehiclesImpl("V-REFRI-1", 600, 30000)); // 30km max
        } catch (VehicleException e) {
            System.out.println("Erro ao inicializar frota de veiculos: " + e.getMessage());
        }

        int option = -1;
        while (option != 0) {
            showMainMenu();
            try {
                System.out.print("Escolha uma opcao: ");
                option = Integer.parseInt(scanner.nextLine().trim());
                switch (option) {
                    case 1:
                        menuVehicles();
                        break;
                    case 2:
                        menuAidBoxes();
                        break;
                    case 3:
                        menuPicking();
                        break;
                    case 0:
                        System.out.println("A sair do programa. Adeus!");
                        break;
                    default:
                        System.out.println("Opcao invalida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduza um numero valido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void showMainMenu() {
        System.out.println("=================================================");
        System.out.println("       SISTEMA DE RECOLHA DE SUPRIMENTOS (PP)     ");
        System.out.println("=================================================");
        System.out.println("1. Frota de Veiculos");
        System.out.println("2. Caixas de Suprimentos (AidBoxes)");
        System.out.println("3. Planeamento de Recolha (Picking)");
        System.out.println("0. Sair");
        System.out.println("=================================================");
    }

    private static void menuVehicles() {
        int option = -1;
        while (option != 0) {
            System.out.println("\n--- Frota de Veiculos ---");
            System.out.println("1. Listar Veiculos");
            System.out.println("2. Adicionar Veiculo Standard");
            System.out.println("3. Adicionar Veiculo Refrigerado");
            System.out.println("4. Ativar Veiculo");
            System.out.println("5. Desativar Veiculo");
            System.out.println("0. Voltar ao Menu Principal");
            try {
                System.out.print("Escolha uma opcao: ");
                option = Integer.parseInt(scanner.nextLine().trim());
                switch (option) {
                    case 1:
                        listVehicles();
                        break;
                    case 2:
                        addStandardVehicle();
                        break;
                    case 3:
                        addRefrigeratedVehicle();
                        break;
                    case 4:
                        changeVehicleState(true);
                        break;
                    case 5:
                        changeVehicleState(false);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void listVehicles() {
        Vehicle[] fleet = institution.getVehicles();
        if (fleet == null || fleet.length == 0) {
            System.out.println("Nenhum veiculo registado.");
            return;
        }
        System.out.println("\nLista de Veiculos na Frota:");
        for (Vehicle v : fleet) {
            System.out.println(v.toString());
        }
    }

    private static void addStandardVehicle() throws VehicleException {
        System.out.print("ID do Veiculo: ");
        String id = scanner.nextLine().trim();
        System.out.println("Tipo de Item:");
        System.out.println("1. NON_PERISHABLE_FOOD");
        System.out.println("2. CLOTHING");
        System.out.println("3. MEDICINE");
        System.out.print("Escolha o tipo: ");
        int typeOpt = Integer.parseInt(scanner.nextLine().trim());
        ItemType type = ItemType.NON_PERISHABLE_FOOD;
        if (typeOpt == 2) type = ItemType.CLOTHING;
        else if (typeOpt == 3) type = ItemType.MEDICINE;

        System.out.print("Capacidade Maxima (kg): ");
        double capacity = Double.parseDouble(scanner.nextLine().trim());

        Vehicle vehicle = new NormalVehicleImpl(id, type, capacity);
        if (institution.addVehicle(vehicle)) {
            System.out.println("Veiculo Standard adicionado com sucesso!");
        } else {
            System.out.println("Erro: Um veiculo com esse ID ja existe.");
        }
    }

    private static void addRefrigeratedVehicle() throws VehicleException {
        System.out.print("ID do Veiculo Refrigerado: ");
        String id = scanner.nextLine().trim();
        System.out.print("Capacidade Maxima (kg): ");
        double capacity = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Distancia Maxima com carga (m): ");
        double maxDist = Double.parseDouble(scanner.nextLine().trim());

        Vehicle vehicle = new RefrigeratedVehiclesImpl(id, capacity, maxDist);
        if (institution.addVehicle(vehicle)) {
            System.out.println("Veiculo Refrigerado adicionado com sucesso!");
        } else {
            System.out.println("Erro: Um veiculo com esse ID ja existe.");
        }
    }

    private static void changeVehicleState(boolean enable) throws VehicleException {
        System.out.print("Introduza o ID do veiculo: ");
        String id = scanner.nextLine().trim();
        Vehicle target = null;
        for (Vehicle v : institution.getVehicles()) {
            if (((VehicleImpl) v).getId().equalsIgnoreCase(id)) {
                target = v;
                break;
            }
        }
        if (target == null) {
            System.out.println("Veiculo nao encontrado.");
            return;
        }

        if (enable) {
            institution.enableVehicle(target);
            System.out.println("Veiculo " + id + " ativado.");
        } else {
            institution.disableVehicle(target);
            System.out.println("Veiculo " + id + " desativado.");
        }
    }

    private static void menuAidBoxes() {
        int option = -1;
        while (option != 0) {
            System.out.println("\n--- Caixas de Suprimentos ---");
            System.out.println("1. Listar AidBoxes e Contentores");
            System.out.println("2. Simular Leituras de Sensores (Aleatorio)");
            System.out.println("3. Registar Medicao Manual");
            System.out.println("0. Voltar");
            try {
                System.out.print("Escolha uma opcao: ");
                option = Integer.parseInt(scanner.nextLine().trim());
                switch (option) {
                    case 1:
                        listAidBoxes();
                        break;
                    case 2:
                        simulateSensors();
                        break;
                    case 3:
                        addManualMeasurement();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void listAidBoxes() {
        AidBox[] boxes = institution.getAidBoxes();
        if (boxes == null || boxes.length == 0) {
            System.out.println("Nenhuma AidBox registada.");
            return;
        }
        System.out.println("\nLista de AidBoxes e Contentores:");
        for (AidBox ab : boxes) {
            System.out.println(ab.toString());
        }
    }

    private static void simulateSensors() {
        AidBox[] boxes = institution.getAidBoxes();
        if (boxes == null || boxes.length == 0) {
            System.out.println("Sem AidBoxes para simular.");
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        int measurementsAdded = 0;

        for (AidBox ab : boxes) {
            for (Container c : ab.getContainers()) {
                // Generate load value between 0 and container capacity
                double maxCap = c.getCapacity();
                double randLoad = random.nextDouble() * maxCap;
                // Round to 1 decimal place
                randLoad = Math.round(randLoad * 10.0) / 10.0;

                Measurement m = new MeasurementImpl(now, randLoad);
                try {
                    institution.addMeasurement(m, c);
                    measurementsAdded++;
                } catch (ContainerException | MeasurementException e) {
                    // ignore if invalid
                }
            }
        }
        System.out.println("Simulacao concluida! Adicionadas " + measurementsAdded + " novas leituras de sensores.");
    }

    private static void addManualMeasurement() throws ContainerException, MeasurementException {
        System.out.print("Codigo da AidBox: ");
        String abCode = scanner.nextLine().trim();
        System.out.println("Tipo de Contentor:");
        System.out.println("1. NON_PERISHABLE_FOOD");
        System.out.println("2. CLOTHING");
        System.out.println("3. MEDICINE");
        System.out.println("4. PERISHABLE_FOOD");
        System.out.print("Escolha o tipo: ");
        int typeOpt = Integer.parseInt(scanner.nextLine().trim());
        ItemType type = ItemType.NON_PERISHABLE_FOOD;
        if (typeOpt == 2) type = ItemType.CLOTHING;
        else if (typeOpt == 3) type = ItemType.MEDICINE;
        else if (typeOpt == 4) type = ItemType.PERISHABLE_FOOD;

        AidBox targetBox = null;
        for (AidBox ab : institution.getAidBoxes()) {
            if (ab.getCode().equalsIgnoreCase(abCode)) {
                targetBox = ab;
                break;
            }
        }
        if (targetBox == null) {
            System.out.println("AidBox nao encontrada.");
            return;
        }

        Container container = targetBox.getContainer(type);
        if (container == null) {
            System.out.println("Contentor desse tipo nao existe nessa AidBox.");
            return;
        }

        System.out.print("Peso a registar (kg, Max: " + container.getCapacity() + "): ");
        double weight = Double.parseDouble(scanner.nextLine().trim());

        Measurement m = new MeasurementImpl(LocalDateTime.now(), weight);
        if (institution.addMeasurement(m, container)) {
            System.out.println("Medicao manual registada com sucesso!");
        } else {
            System.out.println("Erro ao registar medicao.");
        }
    }

    private static void menuPicking() {
        int option = -1;
        while (option != 0) {
            System.out.println("\n--- Planeamento de Recolha ---");
            System.out.println("1. Gerar Rotas Diarias e Relatorio");
            System.out.println("2. Ver Ultimo Mapa de Picking");
            System.out.println("3. Ver Ultimo Relatorio");
            System.out.println("0. Voltar");
            try {
                System.out.print("Escolha uma opcao: ");
                option = Integer.parseInt(scanner.nextLine().trim());
                switch (option) {
                    case 1:
                        generateRoutes();
                        break;
                    case 2:
                        showLastPickingMap();
                        break;
                    case 3:
                        showLastReport();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static Report lastReport = null;

    private static void generateRoutes() throws PickingMapException {
        System.out.println("A gerar rotas baseadas na estrategia de otimizacao...");
        Strategy strategy = new StrategyImpl();
        RouteValidator validator = new RouteValidatorImpl();
        RouteGenerator generator = new RouteGeneratorImpl();
        lastReport = new ReportImpl();

        Route[] routes = generator.generateRoutes(institution, strategy, validator, lastReport);

        System.out.println("Rotas geradas com sucesso! Numero de rotas: " + routes.length);
        System.out.println(lastReport.toString());
    }

    private static void showLastPickingMap() {
        try {
            PickingMap pm = institution.getCurrentPickingMap();
            System.out.println(pm.toString());
        } catch (PickingMapException e) {
            System.out.println("Nenhum mapa de picking gerado ainda.");
        }
    }

    private static void showLastReport() {
        if (lastReport == null) {
            System.out.println("Nenhum relatorio disponivel. Gere as rotas primeiro.");
        } else {
            System.out.println(lastReport.toString());
        }
    }
}
