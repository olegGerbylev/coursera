public class lab_3 {
    public static void main(String[] args) {
        int N = 3; //задаем значение N
        DominoSet set = new DominoSet(); //создаем объект набор домино с помощью класса Nabor_Domino
        Sequence seq = new Sequence(set); //создаем объект последователньость с помощью соответсвующего класса
        System.out.println("Набор:");
        set.print(); //выводим набор доминошек на экран
        System.out.println();
        for (int k = 0; k < 28; k++){
            set.Sequence_make(seq); //создаем последовательность на основе набора
        }
        System.out.println("Последовательность:");
        seq.print(); //выводим последовательность на экран
        System.out.println();
        int currentDomino = -1;
        for(int i = 0; i < 28; i++){
            currentDomino = seq.deleteDomino(N, currentDomino);
        }
    }
}
