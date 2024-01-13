import java.util.Random;

public class DominoSet {
    private Domino[] dominos = new Domino[28];; //у объектов класса Domino будет объект массив доминошек

    public DominoSet() { //конструктор класса, в нем создаем массив из 28 уникальных элементов с помощью цикла for
        for (int i = 0; i < dominos.length; i++) { //само заполение массива
            dominos[i] = new Domino(i);
        }
    }

    public void print() {
        String str="";//метод для вывода доминошек в наборе
        for (Domino p : dominos) //идем по элементам массива доминошек
            str += " " + p;
        System.out.println(str); //для красоты
    }

    public Domino[] getDominos() {
        return dominos;
    }


    public void Sequence_make(Sequence seq) { //метод для создания последовательности, на вход получает последовательность
        int id; //номер элементаx
        Random r=new Random();
        while (true) {//создаем объект класса Random, для рандомного выбора элемента из набора
            id = r.nextInt(28); //присваиваем случайное значение перемнной номера (от 0 до 27)
            if (dominos[id].getNextValue() < 0) { //если значение next у доминошки < 0 (изначально оно -1), то
                seq.add(dominos[id]); //добавляем элемент в последовательность
                return;
            }
        }
    }
}
