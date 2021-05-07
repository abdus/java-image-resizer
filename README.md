# Image Resizer

> A Spring web application for resizing images

## Usage

```
JAVA_HOME=/path/to/java/java-15-openjdk ./mvnw spring-boot:run
```

## API Usage

to resize a Base64 encoded image, make a `POST` call to `/resize` with `base64`
and `scale` as body param.

```
curl --request POST \
  --url https://java-img-resize.herokuapp.com/resize \
  --header 'content-type: application/json' \
  --data '{
	"scale": 0.1,
	"base64": ".... base64 string here ....."
}'
```

> the `scale` param determines whether to scale up or down the image dimensions.
> `scale<1` will reduce the size, and `scale>1` will increase the size.
> `scale` must be greater than 0.

to resize an image available at a remote location:

```
curl --request POST \
  --url https://java-img-resize.herokuapp.com/resize \
  --header 'content-type: application/json' \
  --data '{
	"scale": 0.1,
	"url": "image url here"
}'
```
