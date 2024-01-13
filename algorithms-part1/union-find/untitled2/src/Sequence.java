public class Sequence {
    private Domino end;//объявление переменной last - номер последнего добавленного элемента в последовательности, cnt - количество элементов в последовательности
    private int seqLenght = 0;
    private Domino[] set; //ссылка на оригинальный набор

    public Sequence(DominoSet set) { //конструктор класса, на вход принимает набор, на основе которого построится последовательность
        this.set = set.getDominos(); //указываем набору, что это именно тот набор с доминошками, который создали выше
    }
    public void add(Domino domino) { //метод для добавления элементов в набор, на вход получает объект класса домино (доминошку)
        int i = domino.getCurrentValue(); //i - индекс этой доминошки (от 0 до 27)
        if (end == null) { //если последовательность была пуста (тк last = -1 изначально), то
            end = domino; //значение last = id доминошки
            set[i].setNextValue(i);
            seqLenght++;//добавленная доминошка указывает сама на себя (доминошка в качествее следующей устанавливает себя)
        }
        else { //иначе (последовательность не пустая)
            set[i].setNextValue(end.getNextValue());//добавленная доминошка в качетве следующей запоминает первую доминошку в последоваельности
            set[end.getCurrentValue()].setNextValue(i); //предыдущая доминошка запоминает добавленную, как последний элмент последовательности
            end = set[i]; //обновление последнего для последовательности
            seqLenght++;
        }
        //cnt++; не нужен//увеличение счетчика кол-ва элементов в последовательности
    }

    public void print() { //метод для вывода последовательности
        String str = "";
        if (seqLenght == 0) {
            return;
        }
        Domino currentDomino = set[end.getNextValue()];
        while (currentDomino != end){
            str = str + " " + currentDomino;
            currentDomino = set[currentDomino.getNextValue()];
        }
        str = str + " " + currentDomino;
        System.out.println(str);
    }

    public int deleteDomino(int N, int currentDominoIndex){
        Domino previousDomino = null;
        if (currentDominoIndex == -1){
            currentDominoIndex = end.getCurrentValue();
        }
        Domino currentDomino = set[currentDominoIndex];
        for(int j = 0; j < N; j++){
            previousDomino = currentDomino;
            currentDomino = set[currentDomino.getNextValue()];
        }
        previousDomino.setNextValue(currentDomino.getNextValue());
        Domino deletedDomino = currentDomino;
        System.out.println(deletedDomino);
        seqLenght--;
        if (deletedDomino == end){
            previousDomino.setNextValue(end.getNextValue());
            end = previousDomino;
        } else if (deletedDomino == set[end.getNextValue()]){
            end.setNextValue(set[end.getNextValue()].getNextValue());
        }
        print();
        return previousDomino.getCurrentValue();
    }

}