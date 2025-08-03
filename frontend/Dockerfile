FROM node:18-alpine
WORKDIR /app
COPY package.json tsconfig.json next.config.js next-env.d.ts ./
COPY pages ./pages
RUN npm install && npm run build
EXPOSE 3000
CMD ["npm","start"]
