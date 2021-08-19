package chapter5;

class Ch5_PersonNew_default implements Comparable<Ch5_PersonNew_default>{
    private String name;
    private int age;
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
    public int compareTo(Ch5_PersonNew_default p) {
        return name.compareTo(p.getName());
        //return name.length()-p.getName().length(); // например, по длине имени
    }
    // Интерфейс Comparable содержит один единственный метод int compareTo(E item),
    // который сравнивает текущий объект с объектом, переданным в качестве параметра.
    //
    // Если этот метод возвращает отрицательное число, то текущий объект будет располагаться перед параметром.
    // Если метод вернет положительное число, то, наоборот, после второго объекта.
    //
    // Если метод возвратит ноль, значит, оба объекта равны.
}
