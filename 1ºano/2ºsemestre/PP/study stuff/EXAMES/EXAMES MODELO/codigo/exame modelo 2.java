exame modelo 2

public class RouteImpl implements Route{
    private Vehicle vehicle;
    private AidBox[] aidBoxes;
    private numAidBox;

    private final int MAX_AIDBOX = 10;

    public RouteImpl(Vehicle vehicle){
        this.vehicle = vehicle
        this.aidBoxes = new AidBox[MAX_AIDBOX];
        this.numAidBox = 0;
    }

    @Override
    public Vehicle getVehicle(){
        return this.vehicle;
    }

    @Override
    public void addAidBox(AidBox aidBox) throws RouteException {
            if(aidBox == null){
               throws new RouteException("AidBox não pode ser nula"); 
            }
            if(numAidBox > MAX_AIDBOX){
               throws new RouteException("Maximo de AidBox Atingido"); 
            }
            for(int i = 0; i < numAidBox; i++){
                if(aidBoxes[i].equals(aidBox)){
                    throws new RouteException("Esta AidBox ja existe");
                }
            }

            aidBoxes[numAidBox] = aidBox;
            numAidBox++;
    }

    @Override
    public AidBox removeAidBox(AidBox aidBox) throws RouteException {
            if(aidBox == null){
                throws new RouteException("AidBox não pode ser nula"); 
            }
            for(int i = 0; i < numAidBox; i++){
                if(aidBoxes[i].equals(aidBox)){
                    AidBox removed = aidBoxes[i];
                    for(int j = 0; j < numAidBox; j++){
                        aidBoxes[j] = aidBoxes[j + 1];
                    }
                    aidBoxes[numAidBox - 1] = null;
                    numAidBox--;
                    return removed;
                }
            }
        throw new RouteException("AidBox nao encontrada na rota.");
    }

    @Override
    public AidBox[] getRoute(){
        AidBox[] result = new AidBox[numAidBox];
        for(int i = 0; i < numAidBox; i++){
            result[i] = aidBoxes[i];
        }
        return result
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Route)){
            return false
        }

        Route other = (Route) obj
        return.this.vehicle.getCode().equals(other.getVehicle().getCode());
    }

    @Override
    public String toString(){
        return "RouteImpl[Vehicle: " + getVehicle() + ", AidBoxes: " + numAidBox + "]";
    }
}	

public class Main{
    public static void main(String[] args){
        
        VehicleImpl v1 = new VehicleImpl("V01", ItemType.FOOD, 100);
        VehicleImpl v2 = new VehicleImpl("V02", ItemType.FOOD, 200);
        RouteImpl r1 = new RouteImpl(v1);
        RouteImpl r2 = new RouteImpl(v1);
        RouteImpl r3 = new RouteImpl(v2);

        AidBoxImpl a1 = new AidBoxImpl("A01", "PORTO");
        AidBoxImpl a2 = new AidBoxImpl("A02", "LISBOA");

        System.out.println("Veiculo da da rota: " + r1.getVehicle().getCode());

        try{
            r1.addAidBox(a1);
            System.out.println("AidBox" + a1.getCode()  + "adicionada com sucesso")
            r1.addAidBox(a2);
            System.out.println("AidBox" + a2.getCode()  + "adicionada com sucesso")
        } catch (RouteException e){
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("Numero de AidBoxes na rota: " + r1.getRoute().length);

        try {
            r1.addAidBox(a1);
        } catch (RouteException e) {
            System.out.println("Erro esperado (duplicada): " + e.getMessage());
        }

        try {
            AidBox removida = r1.removeAidBox(a1);
            System.out.println("AidBox removida: " + removida.getCode());
        }catch (RouteException e){
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("Numero de AidBoxes apos remocao: " + r1.getRoute().length);

        System.out.println("r1.equals(r2): " + r1.equals(r2));
        System.out.println("r1.equals(r3): " + r1.equals(r3));
        System.out.println("r1.equals(null): " + r1.equals(null));

        System.out.println("toString: " r1.toString());
    }
}

public class CollectionManagerImpl implements CollectionManager{

    private double getContainerLoad(Container container){
        if(container == null){
            return 0;
        }

        Measurement last = container.getLastMeasurement();
        if(last == null){
            return 0;
        }

        return last.getValue();
    }

    private boolean isContainerFull(Container container, double threshold){
        if(container == null || threshold == null){
            return false;
        }

        Measurement last = container.getLastMeasurement();
        if(last == null){
            return false;
        }

        return last.getValue() > (container.getCapacity() * threshold / 100);

        @Override
        public double getTotalCollectedByType(IInstitution inst, ItemType type){
            if(inst == null || type == null){
                return 0;
            }

            AidBox[] aidboxes = inst.getAidBoxes();
            if(aidBoxes == null){
                return 0;
            }

            double totalCarga = 0;

            for(int i = 0; aidBoxes.length; i++){
                if(aidBoxes[i] == null){
                    return 0;
                }

                Container[] containers = AidBoxes[i].getContainers();
                if(containers == null){
                    return 0;
                }

                for(int j = 0; j < containers.length; j ++){
                    if(isContainerFull(containers[j], 75)){
                        totalCarga += getContainerLoad(containers[j]){

                        }
                    }

                }
            }
        }
        return totalCarga;
    }
}