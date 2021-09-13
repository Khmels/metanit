package chapter5_Collections;

class Ch5_PersonNew_default implements Comparable<Ch5_PersonNew_default>{
    private String name;
    //private int age;
    private Integer age;
    private long id; // ordered

    public Ch5_PersonNew_default(String name, int age, long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public Ch5_PersonNew_default() {
    }

    public Ch5_PersonNew_default(String name) {
        this.name = name;
    }

    public Ch5_PersonNew_default(int age) {
        this.age = age;
    }

    public Ch5_PersonNew_default(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ch5_PersonNotCompare{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(Ch5_PersonNew_default that) {

        //return name.compareTo(p.getName());
        //return name.length()-p.getName().length(); // например, по длине имени

        /*
        Например, для объектов класса User мы задаем сортировку по имени, а в случае равенства имен – по возрасту.
        Объекты будут располагаться в естественном порядке (по мере увеличения значения).
         */
        //используем метод compareTo из класса String для сравнения имен
        int result = this.name.compareTo(that.name);

        //если имена одинаковые -  сравниваем возраст,
        //используя метод compareTo из класса Integer

        if (result == 0) {
            result = this.age.compareTo(that.age);
        }
        return result;
        // как альтернатива thenComparing из Comparator
    }
    // Интерфейс Comparable содержит один единственный метод int compareTo(E item),
    // который сравнивает текущий объект с объектом, переданным в качестве параметра.
    //
    // Если этот метод возвращает отрицательное число, то текущий объект будет располагаться перед параметром.
    // Если метод вернет положительное число, то, наоборот, после второго объекта.
    //
    // Если метод возвратит ноль, значит, оба объекта равны.
}
