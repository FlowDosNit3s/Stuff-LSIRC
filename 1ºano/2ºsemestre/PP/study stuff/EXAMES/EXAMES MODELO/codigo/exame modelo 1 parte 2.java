/**1a)Considere a interface AidBox que representa uma caixa de suprimentos. Implemente a interface
numa classe denominada AidBoxImpl . A AidBox deve possuir um array de Container com
capacidade máxima de 4 contentores. Para a implementação do método equals , considere que
duas instâncias de AidBox são iguais se possuírem o mesmo código (devolvido através do
método getCode() ). Implemente também um método addContainer(Container container) que
adicione um contentor à AidBox, lançando uma exceção caso a capacidade máxima seja atingida.
 */

public class AidBoxImpl implements AidBox {
    private String code;
    private String zone;
    private Container[] container;
    private int containerCount;

    private final int MAX_CONTAINER = 4;

    public AidBoxImpl(String code, String zone) {
        this.code = code;
        this.zone = zone;
        this.container = new Container[MAX_CONTAINER];
        this.containerCount = 0;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getZone() {
        return zone;
    }

    @Override
    public Container[] getContainer() {
        return container;
    }

    public void addContainer(Container container) {
        if(container == null) {
            throw new IllegalArgumentException("Conteinar nao pode ser nulo");
        }
        if(this.container.length >= MAX_CONTAINER) {
            throw new IllegalArgumentException("Nao pode adicionar mais containers");
        }
        
        this.container[this.containerCount] = container;
        this.containerCount++;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AidBox other = (AidBox) obj;

        if (this.code == null) {
        return other.getCode() == null;
        }

        return this.code.equals(other.getCode());
    }

    @Override
    public String toString() {
        return "AidBoxImpl{" +
                "code='" + code + '\'' +
                ", zone='" + zone + '\'' +
                ", containerCount=" + containerCount +
                '}';
    }
}

/*1b)Desenvolva o código necessário para testar a classe implementada (por exemplo, no contexto de
um método main ). Apresente um exemplo de teste para cada método implementado
*/

public class Main {
    public static void main(String[] args){

        //instaciar
        AidBoxImpl v1 = new AidBoxImpl("A123", "Paredes");
        AidBoxImpl v2 = new AidBoxImpl("A123", "Rebordosa");
        AidBoxImpl v3 = new AidBoxImpl("A456", "Paredes");

        //testar gets
        System.out.println("Codigo da AidBox v1: " + v1.getCode());
        System.out.println("Zona da AidBox v1: " + v1.getZone());
        System.out.println("containers da AidBox v1: " + v1.getContainer().length);

        //testar addContainer
        Container c1 = new Container("c001", "comida");
        v1.addContainer(c1);
        if(v1.getContainer().length < MAX_CONTAINER) {
            System.out.println("containers da AidBox v1 apos adicionar c1: " + v1.getContainer().length);
        } else {
            System.out.println("Nao foi possivel adicionar mais containers a AidBox v1");

        //testar equals
        System.out.println("v1.equals(v2): " + v1.equals(v2));
        System.out.println("v1.equals(v3): " + v1.equals(v3));
        System.out.println("v1.equals(null): " + v1.equals(null));
        System.out.println("v1.equals(v1): " + v1.equals(v1));

        //testar toString
        System.out.println("v1.toString(): " + v1.toString());
    }
}

/* 2a)Implemente os seguintes métodos que podem ser utilizados para a geração do relatório na classe
ReportImpl :

A) private int countContainerByType(AidBox aidbox, ItemType type)
Este método deve devolver o número de contentores existentes na AidBox cujo tipo seja igual
ao tipo recebido como argumento

B) private double getAverageOccupancy(AidBox aidbox)
Este método deve devolver a média de ocupação de todos os contentores da AidBox. A
ocupação de cada contentor é calculada através da fórmula: (últimaMedição / capacidade) *
100 . Se um contentor não possuir medições, deve ser ignorado no cálculo da média.
*/

public class ReportImpl implements Report {

    //a
    private int countContainerByType(AidBox aidbox, ItemType type) {
        if(aidbox == null || type == null) {
            return 0;
        }
        Container[] containers = aidbox.getContainer();
        if(containers == null) {
            return 0;
        }
        int count = 0;
        for(int i = 0; containers.length; i++) {
            if(containers[i] != null && containers[i].getType() == type){
                count++;
            }
        }
        return count;
    }
    //b
    private double getAverageOccupancy(AidBox aidbox) {
        if(aidbox == null) {
            return 0;
        }
        Container[] containers = aidbox.getContainer();
        if(containers == null || containers.length == 0) {
            return 0;
        }

        double sum = 0;
        int  count = 0;

        for(int i = 0; i < containers.length; i++) {
            if(containers[i] == null) {
                continue;
            }
            Measurment lastM = containers[i].getMeasurment();
            if(lastM != null){
                double occupanct = (lastM.getValue() / containers[i].getCapacity()) * 100;
                sum += occupanct;
                count++;
            }
        }
        if(count == 0) {
            return 0;
        }
        return sum / count;
    }

/*2b)Na classe ReportImpl , implemente o método generate , gerando um relatório textual.
Regras a considerar:
    -O relatório deve percorrer todas as AidBoxes devolvidas pelo método getAidBoxes() da
    interface IInstitution .
    -Para cada AidBox, deve ser incluída a informação do código, zona e o número de contentores
    por cada tipo (utilizando o método countContainersByType ).
    -Apenas devem ser incluídas AidBoxes cuja ocupação média (utilizando o método
    getAverageOccupancy ) seja superior a 50%.
    -O método generate deve devolver uma String com todo o relatório formatado.
     */

    @Override
    public String generate(IInstitution inst){
        if(inst == null) {
            return "";
        }

        AidBox[] aidBoxes = inst.getAidBoxes();
        if(aidBoxes == null || aidBoxes.lenght == 0) {
            return  "";
        }

        String relatorio = "Relatorio \n";
        int aidBoxesIn=0;
        for(int i = 0; i < aidBoxes.length; i++){
            if(aidBoxes[i] == null){
                continue;
            }
            double avgOcc = getAverageOccupancy(aidBoxes[i]);
            if(avgOcc > 50) {
                relatorio += "AidBox: " + aidBoxes[i].getCode() + 
                            ", Zona: " + aidBoxes[i].getZone() + "\n";
                aidBoxesIn++;
            }
        }
        if(aidBoxesIn == 0) {
            relatorio += "Nao existem AidBoxes com ocupacao media superior a 50%";
        }
        return relatorio;
    }
}

