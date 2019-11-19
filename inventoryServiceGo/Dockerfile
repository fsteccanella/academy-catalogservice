FROM golang:latest AS builder
WORKDIR /app
COPY . .
RUN go get -u github.com/gorilla/mux
RUN CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -a -o /app/inventoryService .

FROM alpine:latest
RUN apk --no-cache add ca-certificates
COPY --from=builder /app/inventoryService /inventoryService
RUN chmod +x /inventoryService
EXPOSE 8080
CMD ["/inventoryService"]