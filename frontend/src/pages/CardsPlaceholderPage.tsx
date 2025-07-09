import { useParams } from "react-router-dom";
import CardPlaceholder from "../components/CardPlaceholder";

const CardsPlaceholderPage = () => {
  const {slugCategoria} = useParams();

  return (
    <>
      <h5>
        {slugCategoria
          ? slugCategoria.charAt(0).toUpperCase() + slugCategoria.slice(1)
          : "Produtos"}
      </h5>
      <div className="row">
        {Array.from({length: 12}).map((_, index) => (
          <div key={index} className="col-6">
            <CardPlaceholder />
          </div>
        ))}
      </div>
    </>
  );
};
export default CardsPlaceholderPage;

// - col-lg-2: 6 elementos por linha em telas grandes (12/2 = 6)
// - col-md-3: 4 elementos por linha em telas médias (12/3 = 4)
// - col-sm-4: 3 elementos por linha em telas pequenas (12/4 = 3)
// - col-6: 2 elementos por linha em telas extra pequenas (12/6 = 2)