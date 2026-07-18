#!/bin/bash
echo "🔪 Matando todos los procesos Java..."
pkill -9 java 2>/dev/null
sleep 2
echo "🚀 Ejecutando servicios..."

# Terminal 1 - Curso Service
osascript -e 'tell app "Terminal" to do script "cd ~/Desktop/cursos-plataforma/curso-service && mvn spring-boot:run"'

# Terminal 2 - Inscripcion Service
osascript -e 'tell app "Terminal" to do script "cd ~/Desktop/cursos-plataforma/inscripcion-service && mvn spring-boot:run"'

# Terminal 3 - API Gateway
osascript -e 'tell app "Terminal" to do script "cd ~/Desktop/cursos-plataforma/api-gateway && mvn spring-boot:run"'

echo "✅ Servicios ejecutándose en terminales separadas"
echo "📋 Puertos: Curso=8081, Inscripcion=8090, Gateway=8080"
