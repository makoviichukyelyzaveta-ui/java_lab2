import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

class Payment {
    private String surname;        // прізвище
    private String name;           // ім'я
    private double salary;         // оклад
    private int hireYear;          // рік поступлення на роботу
    private double bonusPercent;   // відсоток надбавки
    private double taxPercent;     // податок, %
    private int daysWorked;        // кількість відпрацьованих днів у місяці
    private int daysInMonth;       // кількість днів у місяці
    private double accrued;        // нарахована сума
    private double withheld;       // утримана сума

    private static int count = 0;
    public static final double DEFAULT_TAX = 19.5; // ПДФО 18% + військовий збір 1.5%
    {
        surname = "Noname";
        name = "Noname";
        salary = 0;
        hireYear = currentYear();
        bonusPercent = 0;
        taxPercent = DEFAULT_TAX;
        daysWorked = 0;
        daysInMonth = 30;
    }

    public Payment() {
        count++;
    }

    public Payment(String surname, String name, double salary, int hireYear) {
        this.surname = surname;
        this.name = name;
        this.salary = salary;
        this.hireYear = hireYear;
        count++;
    }

    public Payment(String surname, String name, double salary, int hireYear,
                   double bonusPercent, double taxPercent,
                   int daysWorked, int daysInMonth) {
        this.surname = surname;
        this.name = name;
        this.salary = salary;
        this.hireYear = hireYear;
        this.bonusPercent = bonusPercent;
        this.taxPercent = taxPercent;
        this.daysWorked = daysWorked;
        this.daysInMonth = daysInMonth;
        count++;
    }

    public static int getCount() {
        return count;
    }

    public static int currentYear() {
        return LocalDate.now().getYear();
    }
    public double calcAccrued() {
        if (daysInMonth <= 0) return 0;
        accrued = (salary / daysInMonth) * daysWorked * (1.0 + bonusPercent / 100.0);
        return accrued;
    }

    public double calcWithheld() {
        withheld = calcAccrued() * (taxPercent / 100.0);
        return withheld;
    }
    public int getExperience() {
        return currentYear() - hireYear;
    }

    public String getSurname() { return surname; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public int getHireYear() { return hireYear; }
    public double getBonusPercent() { return bonusPercent; }
    public double getTaxPercent() { return taxPercent; }
    public int getDaysWorked() { return daysWorked; }
    public int getDaysInMonth() { return daysInMonth; }
    public double getAccrued() { return accrued; }
    public double getWithheld() { return withheld; }

    public void setSalary(double salary) { this.salary = salary; }
    public void setBonusPercent(double bonusPercent) { this.bonusPercent = bonusPercent; }
    public void setDaysWorked(int daysWorked) { this.daysWorked = daysWorked; }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "Payment[%s %s, оклад=%.2f, рік=%d, надбавка=%.1f%%, податок=%.1f%%, "
                        + "днів=%d/%d, нараховано=%.2f, утримано=%.2f]",
                surname, name, salary, hireYear, bonusPercent, taxPercent,
                daysWorked, daysInMonth, accrued, withheld);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Payment)) return false;
        Payment p = (Payment) obj;
        return Objects.equals(surname, p.surname)
                && Objects.equals(name, p.name)
                && Double.compare(salary, p.salary) == 0
                && hireYear == p.hireYear
                && Double.compare(bonusPercent, p.bonusPercent) == 0
                && Double.compare(taxPercent, p.taxPercent) == 0
                && daysWorked == p.daysWorked
                && daysInMonth == p.daysInMonth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, salary, hireYear, bonusPercent,
                taxPercent, daysWorked, daysInMonth);
    }
}
class Prism {
    private double side;    // сторона правильного трикутника в основі
    private double height;  // висота призми

    private static int count = 0;
    public static final double SQRT3 = Math.sqrt(3);

    {
        side = 1;
        height = 1;
    }

    public Prism() {
        count++;
    }

    public Prism(double side, double height) {
        if (side <= 0 || height <= 0) {
            throw new IllegalArgumentException("Сторона і висота мають бути додатними");
        }
        this.side = side;
        this.height = height;
        count++;
    }

    public Prism(Prism other) {
        this(other.side, other.height);
    }

    public static int getCount() {
        return count;
    }

    /** Площа основи правильного трикутника */
    public double baseArea() {
        return (side * side * SQRT3) / 4.0;
    }

    /** Площа бічної поверхні призми */
    public double lateralArea() {
        return 3.0 * side * height;
    }

    /** Об'єм призми */
    public double volume() {
        return baseArea() * height;
    }

    public double getSide() { return side; }
    public double getHeight() { return height; }

    public void setSide(double side) {
        if (side <= 0) throw new IllegalArgumentException("Сторона має бути додатною");
        this.side = side;
    }

    public void setHeight(double height) {
        if (height <= 0) throw new IllegalArgumentException("Висота має бути додатною");
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Prism[сторона основи=%.2f, висота=%.2f]", side, height);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Prism)) return false;
        Prism p = (Prism) obj;
        return Double.compare(side, p.side) == 0 && Double.compare(height, p.height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side, height);
    }
}
class Complex {
    private double re; // дійсна частина
    private double im; // уявна частина

    private static int count = 0;
    public static final Complex ZERO = new Complex(0, 0);

    {
        re = 0;
        im = 0;
    }

    public Complex() {
        count++;
    }

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
        count++;
    }

    public Complex(double re) {
        this(re, 0);
    }

    public static int getCount() {
        return count;
    }

    public static Complex fromPolar(double modulus, double argument) {
        return new Complex(modulus * Math.cos(argument), modulus * Math.sin(argument));
    }

    public Complex add(Complex o) {
        return new Complex(re + o.re, im + o.im);
    }

    public Complex subtract(Complex o) {
        return new Complex(re - o.re, im - o.im);
    }

    public Complex multiply(Complex o) {
        return new Complex(re * o.re - im * o.im, re * o.im + im * o.re);
    }

    public Complex divide(Complex o) {
        double d = o.re * o.re + o.im * o.im;
        if (d == 0) {
            throw new ArithmeticException("Ділення на нуль (комплексний нуль)");
        }
        return new Complex((re * o.re + im * o.im) / d, (im * o.re - re * o.im) / d);
    }

    /** Модуль комплексного числа */
    public double modulus() {
        return Math.hypot(re, im);
    }

    /** Аргумент комплексного числа */
    public double argument() {
        return Math.atan2(im, re);
    }

    public double getRe() { return re; }
    public double getIm() { return im; }

    @Override
    public String toString() {
        if (im >= 0) return String.format(Locale.US, "%.3f + %.3fi", re, im);
        return String.format(Locale.US, "%.3f - %.3fi", re, -im);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Complex)) return false;
        Complex c = (Complex) obj;
        return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }
}
public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static String[] cmdArgs = new String[0];

    public static void main(String[] args) {
        cmdArgs = args;
        boolean run = true;
        while (run) {
            System.out.println("\n============ ГОЛОВНЕ МЕНЮ (варіант 9) ============");
            System.out.println("1. Завдання 1.9 - Payment (зарплата)");
            System.out.println("2. Завдання 2.9 - Prism (призма)");
            System.out.println("3. Завдання 3.9 - Complex (комплексне число)");
            System.out.println("0. Вихід");
            int choice = readInt("Оберіть завдання: ");
            switch (choice) {
                case 1: runPayment(); break;
                case 2: runPrism(); break;
                case 3: runComplex(); break;
                case 0: run = false; break;
                default: System.out.println("Невірний пункт меню!");
            }
        }
        System.out.println("До побачення!");
        in.close();
    }

    private static void runPayment() {
        System.out.println("\n--- Завдання 1.9: Payment ---");
        System.out.println("Введіть дані працівника:");
        String surname = readString("1. Прізвище: ");
        String name = readString("2. Ім'я: ");
        double salary = readDouble("3. Оклад: ");
        int hireYear = readInt("4. Рік поступлення на роботу: ");
        double bonus = readDouble("5. Відсоток надбавки: ");
        double tax = readDouble("6. Податок, %: ");
        int daysWorked = readInt("7. Кількість відпрацьованих днів: ");
        int daysInMonth = readInt("8. Кількість днів у місяці: ");

        Payment p = new Payment(surname, name, salary, hireYear, bonus, tax, daysWorked, daysInMonth);

        System.out.println("\n--- Результат ---");
        System.out.println("Створено: " + p);
        System.out.printf(Locale.US, "Нарахована сума: %.2f%n", p.calcAccrued());
        System.out.printf(Locale.US, "Утримана сума:   %.2f%n", p.calcWithheld());
        System.out.println("Стаж роботи: " + p.getExperience() + " р.");
        System.out.println("Після розрахунку: " + p);

        Payment same = new Payment(surname, name, salary, hireYear, bonus, tax, daysWorked, daysInMonth);
        System.out.println("p.equals(копія з тими ж даними): " + p.equals(same));
        System.out.println("p.equals(Payment за замовчуванням): " + p.equals(new Payment()));
        System.out.println("Створено об'єктів Payment: " + Payment.getCount());
    }

    private static void runPrism() {
        System.out.println("\n--- Завдання 2.9: Prism ---");
        System.out.println("Введіть дані призми:");
        Prism p = null;
        while (p == null) {
            double side = readDouble("1. Сторона основи (правильний трикутник): ");
            double height = readDouble("2. Висота призми: ");
            try {
                p = new Prism(side, height);
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage() + ". Введіть значення ще раз.");
            }
        }

        System.out.println("\n--- Результат ---");
        System.out.println("Створено: " + p);
        System.out.printf(Locale.US, "Площа основи:          %.3f%n", p.baseArea());
        System.out.printf(Locale.US, "Площа бічної поверхні: %.3f%n", p.lateralArea());
        System.out.printf(Locale.US, "Об'єм:                 %.3f%n", p.volume());

        Prism copy = new Prism(p);
        System.out.println("Копія: " + copy + ", p.equals(copy): " + p.equals(copy));
        copy.setHeight(p.getHeight() * 2);
        System.out.println("Копія після setHeight(2*h): " + copy + ", p.equals(copy): " + p.equals(copy));
        System.out.println("Створено об'єктів Prism: " + Prism.getCount());
    }

    private static void runComplex() {
        System.out.println("\n--- Завдання 3.9: Complex ---");
        double[] v = new double[4];
        boolean fromArgs = false;
        if (cmdArgs.length == 4) {
            try {
                for (int i = 0; i < 4; i++) v[i] = Double.parseDouble(cmdArgs[i]);
                fromArgs = true;
                System.out.println("Числа взято з командного рядка.");
            } catch (NumberFormatException e) {
                System.out.println("Некоректні аргументи командного рядка, введіть числа вручну.");
            }
        }
        if (!fromArgs) {
            v[0] = readDouble("1. Дійсна частина 1-го числа: ");
            v[1] = readDouble("2. Уявна частина 1-го числа: ");
            v[2] = readDouble("3. Дійсна частина 2-го числа: ");
            v[3] = readDouble("4. Уявна частина 2-го числа: ");
        }

        Complex z1 = new Complex(v[0], v[1]);
        Complex z2 = new Complex(v[2], v[3]);

        System.out.println("\n--- Результат ---");
        System.out.println("z1 = " + z1);
        System.out.println("z2 = " + z2);
        System.out.println("z1 + z2 = " + z1.add(z2));
        System.out.println("z1 - z2 = " + z1.subtract(z2));
        System.out.println("z1 * z2 = " + z1.multiply(z2));
        try {
            System.out.println("z1 / z2 = " + z1.divide(z2));
        } catch (ArithmeticException e) {
            System.out.println("z1 / z2: " + e.getMessage());
        }
        System.out.printf(Locale.US, "|z1| = %.4f, arg(z1) = %.4f рад%n", z1.modulus(), z1.argument());
        System.out.printf(Locale.US, "|z2| = %.4f, arg(z2) = %.4f рад%n", z2.modulus(), z2.argument());
        System.out.println("z1 з полярної форми: " + Complex.fromPolar(z1.modulus(), z1.argument()));
        System.out.println("z1.equals(z2): " + z1.equals(z2));
        System.out.println("Створено об'єктів Complex: " + Complex.getCount());
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return in.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Введіть ціле число!");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readString(prompt).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Введіть число!");
            }
        }
    }
}