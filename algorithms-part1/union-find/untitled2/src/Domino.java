public class Domino {
    private int currentValue;
    private int nextValue= -1; //объявляем переменные id-номер элемента, next - номер следующего за ним в последовательности


    public Domino(int x) { //конструктор класса, получает на вход номер элемента
        currentValue = x; //присваиваем текущему объекту номер
    }

    public int getNextValue() {
        return nextValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setNextValue(int nextValue) {
        this.nextValue = nextValue;
    }

    @Override
    public String toString() { //метод для вывода пары чисел для кости
        return currentValue / 4 + "|" + (currentValue % 4 + currentValue / 4) % 7;
    }
}

