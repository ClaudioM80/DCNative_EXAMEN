#!/bin/bash
echo "🔪 Matando procesos Java..."
pkill -9 java 2>/dev/null
sleep 1
echo "🚀 Ejecutando Inscripcion Service en puerto 8088..."
cd ~/Desktop/cursos-plataforma/inscripcion-service
mvn spring-boot:run
