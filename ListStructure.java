import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

// to represent any type of list
interface IList<T> extends IConstants {

  //filter this IList using the given predicate
  IList<T> filter(Predicate<T> pred);

  //map the given function onto every member of this IList
  <U> IList<U> map(Function<T,U> converter);

  //combine the items in this IList using the given function
  <U> U fold(BiFunction<T,U,U> converter,U initial);

  //checks if at everything in the list passes the predicate
  boolean andmap(Predicate<T> pred);

  // checks if at least one element in the list passes the predicate
  boolean ormap(Predicate<T> pred);

  // Picks a elements in the list according to the index given
  T pick(Integer initial);
}

// To represent and empty list
class MtList<T> implements IList<T> {
  /* Template:
   * Methods:
   * ... this.filter(Predicate<T>)         ... - IList<T>
   * ... this.map(Function<T, u>)          ... - IList<U>
   * ... this.fold(BiFunction<T, U, U>, U) ... - IList<U>
   * ... this.andmap(Predicate<T>) ...         - boolean
   * ... this.ormap(Predicate<T>) ...          - boolean
   * ... this.pick(Integer) ...                - T
   */

  MtList() {}

  // filter this MtList using the given predicate
  public IList<T> filter(Predicate<T> pred) {
    return new MtList<T>();
  }

  // map the given function onto every member of this MtList
  public <U> IList<U> map(Function<T, U> converter) {
    return new MtList<U>();
  }

  // combine the items in this MtList using the given function
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    return initial;
  }

  // checks if everything in the list passes the predicate
  public boolean andmap(Predicate<T> pred) {
    return true;
  }

  // checks if at least one element in the list passes the predicate
  public boolean ormap(Predicate<T> pred) {
    return false;
  }

  // finds the ith element in the list
  public T pick(Integer initial) {
    throw new IllegalArgumentException("No object found");
  }
}

// To represent a non-empty list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first,IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  /* Template:
   * Fields:
   * ... this.first ... - T
   * ... this.rest ...  - IList<T>
   * Methods:
   * ... this.filter(Predicate<T>) ...         - IList<T>
   * ... this.map(Function<T, u>) ...          - IList<U>
   * ... this.fold(BiFunction<T, U, U>, U) ... - IList<U>
   * ... this.andmap(Predicate<T>) ...         - boolean
   * ... this.ormap(Predicate<T>) ...          - boolean
   * ... this.pick(Integer) ...                - T
   * Methods of fields:
   * ... this.rest.filter(Predicate<T>) ...         - IList<T>
   * ... this.rest.map(Function<T, u>) ...          - IList<U>
   * ... this.rest.fold(BiFunction<T, U, U>, U) ... - IList<U>
   * ... this.rest.andmap(Predicate<T>) ...         - boolean
   * ... this.rest.ormap(Predicate<T>) ...          - boolean
   * ... this.rest.pick(Integer) ...                - T
   */

  //filter this ConsList using the given predicate
  public IList<T> filter(Predicate<T> pred) {
    if (pred.test(this.first)) {
      return new ConsList<T>(this.first,this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  //map the given function onto every member of this ConsList
  public <U> IList<U> map(Function<T, U> converter) {
    return new ConsList<U>(converter.apply(this.first),this.rest.map(converter));
  }

  //combine the items in this ConsList using the given function
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    return converter.apply(this.first, this.rest.fold(converter,initial));
  }

  // checks if everything in the list passes the predicate
  public boolean andmap(Predicate<T> pred) {
    return pred.test(this.first) && this.rest.andmap(pred);
  }

  // checks if at least one element in the list passes the predicate
  public boolean ormap(Predicate<T> pred) {
    return pred.test(this.first) || this.rest.ormap(pred);
  }

  // gets the ith element in the list
  public T pick(Integer initial) {
    if (initial == 0) {
      return this.first;
    }
    else {
      return this.rest.pick(initial - 1);
    }
  }
}
