
# Nombre de la imagen
IMAGE_NAME="app_demo"

# Nombre del contenedor (opcional)
CONTAINER_NAME="app_demo_container"

# Construir la imagen
echo "Construyendo la imagen Docker..."
docker build -t $IMAGE_NAME .

# Confirmar que la imagen se construyó correctamente
if [ $? -eq 0 ]; then
  echo "Imagen construida exitosamente: $IMAGE_NAME"
else
  echo "Error al construir la imagen"
  exit 1
fi

# (Opcional) Ejecutar el contenedor
# echo "Ejecutando el contenedor..."
# docker run -d -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME
