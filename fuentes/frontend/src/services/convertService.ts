export type ConvertEndpoint =
  | "txt-to-json"
  | "txt-to-xml"
  | "json-to-txt"
  | "xml-to-txt";

const getFileFieldName = (endpoint: ConvertEndpoint): string => {
  switch (endpoint) {
    case "json-to-txt":
      return "jsonFile";
    case "xml-to-txt":
      return "xmlFile";
    default:
      return "txtFile";
  }
};

export const convertFile = async (
  endpoint: ConvertEndpoint,
  file: File,
  delimiter: string,
  encryptionKey: string
): Promise<Blob> => {
  const formData = new FormData();
  const fileField = getFileFieldName(endpoint); // 🔁 Campo correcto
  formData.append(fileField, file);
  formData.append("delimiter", delimiter);
  formData.append("encryptionKey", encryptionKey);

  const response = await fetch(`http://localhost:8080/api/convertors/${endpoint}`, {
    method: "POST",
    body: formData,
  });

if (!response.ok) {
  const text = await response.text();

  // Otro error de validación
  if (text.includes("encryptionKey")) {
    throw new Error("Uno de los campos del formulario contiene un valor no válido.");
  }

  // Error genérico
  throw new Error(`Error al procesar la solicitud (${response.status})`);
}



  return await response.blob();
};
