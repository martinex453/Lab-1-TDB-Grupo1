<template>
    <div class="full-container">
        <div class="procedure-container">
            <h1>
                {{ isDiscountView ? 'Aplicar Descuento' : isTopClientsView ? 'Obtener usuarios con más querys' : 'Top 5 Usuarios que más gastaron en Tecnología' }}
            </h1>
            <div class="switch-container">
                <button @click="toggleView('discount')" :class="{ active: isDiscountView }">Aplicar Descuento</button>
                <button @click="toggleView('topClients')" :class="{ active: isTopClientsView }">Obtener usuarios con más querys</button>
                <button @click="toggleView('topSpenders')" :class="{ active: isTopSpendersView }">Top 5 Usuarios en Tecnología</button>
            </div>
            <div class="action-container">
                <div v-if="isDiscountView" class="discount-container">
                    <form @submit.prevent="applyDiscount">
                        <select v-model="selectedCategory">
                            <option v-for="category in categories" :key="category.id" :value="category">
                                {{ category.nombre }}
                            </option>
                        </select>
                        <input type="number" step="0.001" min="0" max="1" v-model="discount" placeholder="Ingrese el descuento" class="styled-input" required>
                        <button type="submit">Aplicar Descuento</button>
                    </form>
                </div>
                <div v-else-if="isTopClientsView" class="top-clients-container">
                    <button @click="fetchTopClients">Obtener usuarios con más querys</button>
                </div>
                <div v-else class="top-spenders-container">
                    <button @click="fetchTopSpenders">Top 5 Usuarios en Tecnología</button>
                </div>
            </div>
        </div>
        <div v-if="results" class="results-container">
        <h2>Resultados</h2>
        <pre class="results-text">{{ results }}</pre>
    </div>

    </div>
</template>

<script>
import categoryService from '../services/categoryService';
import clienteService from "../services/clientServices.js"; 
import productService from "../services/productService.js";
export default {
    //Definir las propiedades del componente
    data() {
        return {
            isDiscountView: true,
            isTopClientsView: false,
            isTopSpendersView: false,
            selectedCategory: null,
            categories: [],
            results: '',
            discount: 0
        };
    },
    methods: {
        toggleView(view) {
            //Cambiar la vista del procedimiento almacenado
            this.isDiscountView = view === 'discount';
            this.isTopClientsView = view === 'topClients';
            this.isTopSpendersView = view === 'topSpenders';
            //Limpiar los resultados al cambiar de vista
            this.results = '';
        },
        async applyDiscount() {
            //Aplicar un descuento a una categoría de productos
            await productService.applyDiscount(this.$cookies.get("jwt"), this.selectedCategory.id_categoria, this.discount);
            alert("Descuento aplicado correctamente");
        },
        async fetchTopClients() {
            //Obtener los usuarios con más querys
            const response = await clienteService.getTopUsers(this.$cookies.get("jwt"));
            //Asignar el string directamente
            this.results = response.data; 
        },
        async fetchTopSpenders() {
            //Obtener los usuarios que más gastaron en tecnología
            const response = await clienteService.getTopSpenders(this.$cookies.get("jwt"));
            //Formatear los resultados
            this.results = response.data.map((user, index) => `${index + 1}. ${user.cliente}, total gastado:${user.totalgastado}`).join('\n');
        },
        async loadCategories() {
            //Obtener las categorías de productos y asignarlas a la variable
            const response = await categoryService.getAll(this.$cookies.get("jwt"));
            this.categories = response.data;
        }
    },
    mounted() {
        //Cargar las categorías al cargar el componente
        this.loadCategories();
    }
}
</script>

<style scoped>
.full-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width: 100%;
    gap: 20px;
    margin-top: 20px;
}

.procedure-container {
    display: flex;
    flex-direction: column;
    gap: 20px;
    padding: 20px;
    background-color: #f0f0f0;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    width: 800px;
}

.switch-container {
    display: flex;
    justify-content: space-between;
}

.switch-container button {
    flex: 1;
    padding: 10px;
    margin: 0 5px;
    background-color: #3b82f6;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.switch-container button.active {
    background-color: #2563eb;
}

.action-container {
    display: flex;
    flex-direction: column;
}

.discount-container,
.top-clients-container,
.top-spenders-container {
    flex: 1;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
}

.results-container {
    width: 80%;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    max-height: 300px;
    overflow-y: auto;
    margin-top: 20px;
}

.results-text {
    color: #000;
    font-size: 16px; 
    line-height: 1.5; 
    white-space: pre-wrap; 
}


button {
    width: 100%;
    padding: 12px;
    background-color: #3b82f6;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
    margin-top: 10px;
}

button:hover {
    background-color: #2563eb;
}

select {
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #d1d5db;
    margin-bottom: 10px;
}

.styled-input {
    width: 100%;
    padding: 12px;
    margin-bottom: 10px;
    border: 1px solid #d1d5db;
    border-radius: 5px;
    font-size: 16px;
    transition: border-color 0.3s, box-shadow 0.3s;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.styled-input:focus {
    border-color: #3b82f6;
    box-shadow: 0 0 8px rgba(59, 130, 246, 0.5);
    outline: none;
}
</style>