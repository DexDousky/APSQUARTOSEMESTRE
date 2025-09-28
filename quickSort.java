public class quickSort {

    // 1. Método público principal para iniciar a ordenação
    // Chama o método auxiliar recursivo 'quickSort' com os limites iniciais
    public void sort(int[] array) {
        if (array == null || array.length == 0) {
            return; // Nada a ordenar
        }
        quickSort(array, 0, array.length - 1);
    }

    // 2. Método auxiliar recursivo (a essência do algoritmo)
    private void quickSort(int[] array, int inicio, int fim) {
        // A condição de parada da recursão
        if (inicio < fim) {
            // 'pi' é o índice de particionamento, array[pi] está no lugar certo
            int pi = partition(array, inicio, fim);

            // Ordena recursivamente os elementos antes e depois do particionamento
            quickSort(array, inicio, pi - 1);  // Sub-array da esquerda
            quickSort(array, pi + 1, fim); // Sub-array da direita
        }
    }

    // 3. Método de Particionamento (o coração do Quicksort)
    // Coloca o pivô no lugar correto e move elementos menores para a esquerda
    // e maiores para a direita. Retorna o índice do pivô.
    private int partition(int[] array, int low, int high) {
        // Escolhe o último elemento como o pivô (existem outras estratégias)
        int pivot = array[high]; 
        
        // 'i' é o índice do menor elemento
        int i = (low - 1); 

        for (int j = low; j < high; j++) {
            // Se o elemento atual é menor ou igual ao pivô
            if (array[j] <= pivot) {
                i++;

                // Troca array[i] e array[j]
                swap(array, i, j);
            }
        }

        // Troca array[i+1] e array[high] (coloca o pivô no lugar certo)
        swap(array, i + 1, high);

        return i + 1; // Retorna a posição final do pivô
    }

    // 4. Método auxiliar para trocar dois elementos no array
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // --- Exemplo de uso para teste ---
    /*
    public static void main(String[] args) {
        int[] data = {10, 7, 8, 9, 1, 5};
        QuickSort qs = new QuickSort();
        qs.sort(data);
        System.out.println("Array ordenado:");
        for (int val : data) {
            System.out.print(val + " ");
        }
        // Saída esperada: 1 5 7 8 9 10
    }
    */
}