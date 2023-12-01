public class SemaphoreExample {
    public static void main(String[] args) {
        CustomSemaphore semaphore = new CustomSemaphore(3, 5000); // Создаем семафор с лимитом 3 и временным ограничением 5 секунд

        // Создаем потоки, которые пытаются получить доступ к ресурсу
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire(); // Захватываем разрешение
                    System.out.println(Thread.currentThread().getName() + " captured the permission");
                    Thread.sleep(2000); // Имитируем выполнение работы
                    semaphore.release(); // Освобождаем разрешение
                    System.out.println(Thread.currentThread().getName() + " released the permission");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }
}