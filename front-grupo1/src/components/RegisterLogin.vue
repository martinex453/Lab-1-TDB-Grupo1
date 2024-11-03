<script>

export default {
    data() {
        return {
            register: true,
            name: '',
            email: '',
            address: '',
            phone: ''
        }
    },
    methods: {
        submitForm() {
            console.log(this.name, this.email, this.address, this.phone)
        }
    }
}
</script>

<template>
    <div class="container-register-login">
        <div class="content">
            <h1>Bienvenido al sistema de gestion</h1>
            <br>
            <div class="form-container">
                <h1>REGISTRO</h1>
                <form v-if="register">
                    <label class="form-group-elem">Nombre</label>
                    <div class="form-group">
                        <input class="form-group-elem" type="text" placeholder="Ingrese su nombre" id="name" />
                    </div>
                    <br>
                    <label class="form-group-elem">Correo</label>
                    <div class="form-group">
                        <input class="form-group-elem" type="email" placeholder="Ingrese su correo" id="email" />
                    </div>
                    <br>
                    <label class="form-group-elem">Direccion</label>
                    <div class="form-group">
                        <input class="form-group-elem" type="text" placeholder="Ingrese su direccion" id="address" />
                    </div>
                    <br>
                    <label class="form-group-elem">Telefono</label>
                    <div class="form-group">
                        <input class="form-group-elem" type="phone" placeholder="Ingrese su telefono" id="phone" />
                    </div>
                    <br>
                    <button @click="submitForm" class="form-group-elem">Registrar</button>
                    <a @click="register = false">Iniciar sesion</a>
                </form>
                <form v-else>
                    <h1>INICIO DE SESION</h1>
                    <label class="form-group-elem">Nombre</label>
                    <div class="form-group">
                        <input class="form-group-elem" type="text" placeholder="Ingrese su nombre" id="name" />
                    </div>
                    <br>
                    <label class="form-group-elem">Correo</label>
                    <div class="form-group">
                        <input class="form-group-elem" type="email" placeholder="Ingrese su correo" id="email" />
                    </div>
                    <br>
                    <button @click="submitForm" class="form-group-elem">Iniciar sesion</button>
                    <a @click="register = true">Registrarse</a>
                </form>
            </div>
        </div>
    </div>
</template>

<style>
.container-register-login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: chocolate;
    width: 100%;
    overflow: hidden;
}

.content {
    width: 100%;
    max-width: 600px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: rgb(228, 151, 96);
}

.form-container {
    width: 100%;
    max-width: 400px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin: auto;
    background-color: rgb(104, 49, 9);
}

.form-group {
    display: flex;
    align-items: center;
}

.form-group-elem {
    padding: 10px;
    margin: auto;
    width: 100%;
    border-radius: 5px;
}

h1 {
    text-align: center;
}

a {
    text-align: center;
    display: block;
    margin-top: auto;
    cursor: pointer;
}
</style>