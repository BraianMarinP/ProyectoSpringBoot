package co.edu.uniquindio.moonmarket.servicios.implementaciones;

import co.edu.uniquindio.moonmarket.dto.ImagenDTO;
import co.edu.uniquindio.moonmarket.servicios.interfaces.CloudinaryServicio;
import co.edu.uniquindio.moonmarket.servicios.interfaces.ImagenServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServicioImpl implements CloudinaryServicio{
    private final Cloudinary cloudinary;

    public CloudinaryServicioImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dljinqrho");
        config.put("api_key", "277741818174415");
        config.put("api_secret", "Q8tk0UPHyln1lL7LfnQQ0SDLcQQ");
        cloudinary = new Cloudinary(config);
    }
    @Override
    public Map subirImagen(File file, String carpeta) throws Exception{

        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder",
                String.format("uniquindio/%s", carpeta)));
    }
    @Override
    public Map eliminarImagen(ImagenDTO idImagen) throws Exception{
        return cloudinary.uploader().destroy(idImagen.getId(), ObjectUtils.emptyMap());
    }
    @Override
    public File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}